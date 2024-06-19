package team.teampotato.ruok.forge.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Render {
    private static final HashSet<EntityType<?>> blackListCache = new HashSet<>();
    private static final HashSet<EntityType<?>> whiteListCache = new HashSet<>();
    private static int maxRenderedEntities; // 最大渲染生物数量
    private static double[] closestDistances = new double[maxRenderedEntities]; // 记录最近生物的距离
    private static Entity[] closestEntities = new Entity[maxRenderedEntities]; // 记录最近生物

    static {
        initializeArrays();
        initMobList();
    }
    // 初始化数组
    private static void initializeArrays() {
        maxRenderedEntities = RuOK.get().maxEntityEntities;//将此部分放到Init防止多次调用
        closestDistances = new double[maxRenderedEntities];
        closestEntities = new Entity[maxRenderedEntities];
        Arrays.fill(closestDistances, Double.MAX_VALUE);
        Arrays.fill(closestEntities, null);
    }
    // 初始化生物列表
    private static void initMobList() {
        List<String> blackConfig = RuOK.get().blackListedEntities;
        List<String> whiteConfig = RuOK.get().whiteListedEntities;
        for (String bc : blackConfig) {
            Optional<EntityType<?>> entityTypeOpt = EntityType.get(bc);
            if (entityTypeOpt.isPresent()) {
                EntityType<?> entityType = entityTypeOpt.get();
                blackListCache.add(entityType);
            }
        }
        for (String wc : whiteConfig) {
            Optional<EntityType<?>> entityTypeOpt = EntityType.get(wc);
            if (entityTypeOpt.isPresent()) {
                EntityType<?> entityType = entityTypeOpt.get();
                whiteListCache.add(entityType);
            }
        }
    }

    public static boolean isBlacklisted(@NotNull Entity entity) {
        return blackListCache.contains(entity.getType()); // 返回 true 表示在黑名单中 - 删除渲染
    }

    public static boolean isWhitelisted(@NotNull Entity entity) {
        return whiteListCache.contains(entity.getType()); // 返回 true 表示在白名单中 - 添加渲染
    }


    public static Optional<Pair<Text, String>> getTotalCountForDisplay(@NotNull ItemEntity entity) {
        ItemStack stack = entity.getStack();
        int total = stack.getCount();
        String itemName = stack.getName().getString(); // 获取物品名称

        String displayText = getColorForCount(total) + itemName + "x" + total;

        // 创建文本对象
        Text text = Text.of(displayText);

        return !RuOK.get().isAlwaysShowItemCount && total <= stack.getMaxCount() ? Optional.empty() : Optional.of(Pair.of(text, displayText));
    }

    private static String getColorForCount(int count) {
        if (count == 64) return Color.Red;
        else if (count > 32) return Color.Orange;
        else if (count > 16) return Color.Green;
        else return Color.LightGreen;
    }



    // 重新加载方法，更新最大渲染生物数量
    public static void reloadRenderEntity() {
        maxRenderedEntities = RuOK.get().maxEntityEntities;
        initializeArrays();
    }

    public static void entityCull(Entity entity, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientWorld world = mc.world;

        if (world != null && mc.player != null) {
            double distanceToPlayer = entity.squaredDistanceTo(mc.player);

            // 白名单优先：如果实体在白名单中，则保留渲染
            if (isWhitelisted(entity)) {
                return;
            }

            // 黑名单检查：如果实体在黑名单中，则取消渲染
            if (isBlacklisted(entity)) {
                ci.cancel();
                return;
            }

            // 检查是否在最近的生物列表中
            boolean inClosestEntities = isInClosestEntities(entity, distanceToPlayer);

            // 如果实体距离玩家超过指定的距离或不在最近的生物列表中，则取消渲染
            if (distanceToPlayer > RuOK.get().entitiesDistance * RuOK.get().entitiesDistance || !inClosestEntities) {
                ci.cancel();
            }
        }
    }

    private static boolean isInClosestEntities(Entity entity, double distanceToPlayer) {
        // 遍历最近的生物列表，检查是否在列表中
        for (int i = 0; i < maxRenderedEntities; i++) {
            if (closestEntities[i] == entity) {
                return true;
            }
        }

        // 如果不在列表中，则尝试将其加入列表
        for (int i = 0; i < maxRenderedEntities; i++) {
            if (closestEntities[i] == null) { // 找到空位
                closestEntities[i] = entity;
                closestDistances[i] = distanceToPlayer;
                return true;
            } else if (distanceToPlayer < closestDistances[i]) { // 找到比当前生物更近的生物替换
                shiftArrayElements(i);
                closestEntities[i] = entity;
                closestDistances[i] = distanceToPlayer;
                return true;
            }
        }

        return false; // 如果列表已满且没有更近的位置
    }

    private static void shiftArrayElements(int startIndex) {
        // 向后移动数组元素，为更近的生物腾出位置
        for (int i = maxRenderedEntities - 1; i > startIndex; i--) {
            closestEntities[i] = closestEntities[i - 1];
            closestDistances[i] = closestDistances[i - 1];
        }
    }

    private static boolean modsCullEntity(@NotNull Entity entity){
        String getClass = entity.getClass().getName();
        return
                getClass.startsWith("com.simibubi.create.content.contraptions")
                        || entity instanceof GhastEntity
                        || entity instanceof EnderDragonEntity;

    }

}
