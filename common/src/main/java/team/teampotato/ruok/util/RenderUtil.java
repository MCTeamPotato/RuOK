package team.teampotato.ruok.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;

import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;

import java.util.*;

public class RenderUtil {
    public static boolean isBlacklisted(@NotNull Entity entity) {
        // 检查生物是否在黑名单中
        List<String> config = RuOK.get().blackListedEntities;
        String entityName = Objects.requireNonNull(EntityType.getId(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在黑名单中 - 删除渲染
    }

    public static boolean isWhitelisted(@NotNull Entity entity) {
        // 检查生物是否在白名单中
        List<String> config = RuOK.get().whiteListedEntities;
        String entityName = Objects.requireNonNull(EntityType.getId(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在白名单中 - 添加渲染
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

    public static void entityCull(Entity entity, CallbackInfo ci) {//不要优化
        MinecraftClient mc = MinecraftClient.getInstance();
        World world = mc.world;
        if (world != null && mc.player != null) {
            Box box = mc.player.getBoundingBox().expand(RuOK.get().entitiesDistance);
            List<LivingEntity> livingEntities = world.getEntitiesByClass(LivingEntity.class, box, e -> true);
            List<Entity> entityEntities = world.getEntitiesByClass(Entity.class, box, e -> true); // 获取所有实体类型
            livingEntities.sort(Comparator.comparingDouble(e -> e.squaredDistanceTo(mc.player)));

            if (isEntityCull(box,entity,entityEntities)
                    || (isLivingCull(entity, box, livingEntities) && isBlacklisted(entity))
                    || isWhitelisted(entity)) {
                ci.cancel();
            }
        }
    }

    private static boolean isLivingCull(@NotNull Entity entity, @NotNull Box box, List<LivingEntity> livingEntity) {
        // 如果实体不是 LivingEntity 类型，直接返回 true
        if (!(entity instanceof LivingEntity livingEntityInstance)) return false;
        return !box.contains(entity.getPos()) &&
                    !livingEntity.contains(livingEntityInstance) &&
                    (livingEntity.indexOf(livingEntityInstance) < RuOK.get().maxLivingEntities);
    }

    private static boolean isEntityCull(@NotNull Box box, @NotNull Entity entity, List<Entity> entityList) {
        // 如果实体列表为 null 或不包含该实体，则返回 true
        if (entityList == null || !entityList.contains(entity)) return false;
        // 检查实体是否在范围内，并确保实体的索引小于最大允许的实体数量
        return !box.contains(entity.getPos()) && entityList.indexOf(entity) < RuOK.get().maxEntityEntities;
    }


    private static boolean ModsCullEntity(@NotNull Entity entity){
        String getClass = entity.getClass().getName();
        return
                getClass.startsWith("com.simibubi.create.content.contraptions")
                        || entity instanceof GhastEntity
                        || entity instanceof EnderDragonEntity;

    }




}
