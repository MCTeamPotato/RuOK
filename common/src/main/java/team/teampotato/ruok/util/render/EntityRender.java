package team.teampotato.ruok.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Ghast;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class EntityRender {
    private static final HashSet<EntityType<?>> blackEntityListCache = new HashSet<>();
    private static final HashSet<EntityType<?>> whiteEntityListCache = new HashSet<>();
    private static int maxRenderedEntities; // 最大渲染生物数量
    private static double[] closestDistances; // 记录最近生物的距离
    private static Entity[] closestEntities; // 记录最近生物
    private static final Minecraft mc = Minecraft.getInstance();


    static {
        initConfigList();
        initializeArrays();
    }

    // 初始化数组
    private static void initializeArrays() {
        maxRenderedEntities = RuOK.get().MaxEntityEntities; // 从配置中获取最大渲染生物数量
        closestDistances = new double[maxRenderedEntities];
        closestEntities = new Entity[maxRenderedEntities];
        Arrays.fill(closestDistances, Double.MAX_VALUE);
        Arrays.fill(closestEntities, null);
    }

    // 初始化生物列表
    private static void initConfigList() {
        List<String> blackConfig = RuOK.get().blackListedEntities;
        List<String> whiteConfig = RuOK.get().whiteListedEntities;
        for (String bc : blackConfig) {
            Optional<EntityType<?>> entityTypeOpt = entityTypeGet(bc);
            entityTypeOpt.ifPresent(blackEntityListCache::add);
        }
        for (String wc : whiteConfig) {
            Optional<EntityType<?>> entityTypeOpt = entityTypeGet(wc);
            entityTypeOpt.ifPresent(whiteEntityListCache::add);
        }
    }
    public static Optional<EntityType<?>> entityTypeGet(String id) {
        return BuiltInRegistries.ENTITY_TYPE.getOptional(ResourceLocation.tryParse(id));
    }

    // 重新加载生物列表
    public static void reloadList() {
        // 清空当前的缓存
        blackEntityListCache.clear();
        whiteEntityListCache.clear();
        // 重新加载生物列表
        initConfigList();

    }

    public static boolean isBlacklisted(@NotNull Entity entity) {
        return blackEntityListCache.contains(entity.getType()); // 返回 true 表示在黑名单中 - 删除渲染
    }
    public static boolean isWhitelisted(@NotNull Entity entity) {
        return whiteEntityListCache.contains(entity.getType()); // 返回 true 表示在白名单中 - 添加渲染
    }

    // 重新加载方法，更新最大渲染生物数量
    public static void reloadRenderEntity() {
        maxRenderedEntities = RuOK.get().MaxEntityEntities;
        initializeArrays();
    }

    public static void entityCull(Entity entity, CallbackInfo ci) {
        // 剔除功能,如果未开启就关闭
        if(!RuOK.get().EntityRender) {
            ci.cancel();
            return;
        }
        if(!RuOK.get().onCull) return;
        if (mc.level != null && mc.player != null) {
            double distanceToPlayer = entity.distanceToSqr(mc.player);

            // 白名单优先：如果实体在白名单中，则保留渲染
            if (isWhitelisted(entity)) {
                return;
            }
            // 如果有原版BOSS，则继续渲染
            if (isBossEntity(entity)) {
                return;
            }

            // 黑名单检查：如果实体在黑名单中，则取消渲染
            if (isBlacklisted(entity)) {
                ci.cancel();
                return;
            }

            // 检查是否在最近的生物列表中
            boolean inClosestEntities = updateClosestEntities(entity, distanceToPlayer);

            // 如果实体距离玩家超过指定的距离或不在最近的生物列表中，则取消渲染
            if (distanceToPlayer > RuOK.get().EntitiesDistance * RuOK.get().EntitiesDistance || !inClosestEntities) {
                ci.cancel();
            }
        }
    }


    private static boolean updateClosestEntities(Entity entity, double distanceToPlayer) {
        int farthestIndex = -1;
        double maxDistance = -1;

        // 检查并移除失效的实体
        for (int i = 0; i < maxRenderedEntities; i++) {
            if (closestEntities[i] != null && !closestEntities[i].isAlive()) {
                closestEntities[i] = null;
                closestDistances[i] = Double.MAX_VALUE;
            }
        }

        // 检查实体是否已经在最近生物列表中
        for (int i = 0; i < maxRenderedEntities; i++) {
            if (closestEntities[i] == entity) {
                // 如果实体已经在列表中，更新距离
                closestDistances[i] = distanceToPlayer;
                return true;
            }
            if (closestEntities[i] == null || closestDistances[i] > maxDistance) {
                farthestIndex = i;
                maxDistance = closestDistances[i];
            }
        }

        // 尝试将实体加入列表
        if (farthestIndex != -1 && (closestEntities[farthestIndex] == null || distanceToPlayer < maxDistance)) {
            closestEntities[farthestIndex] = entity;
            closestDistances[farthestIndex] = distanceToPlayer;
            return true;
        }

        return false; // 如果列表已满且没有更近的位置
    }

    private static boolean isBossEntity(@NotNull Entity entity) {
        return entity instanceof Ghast || entity instanceof EnderDragon;
    }


}
