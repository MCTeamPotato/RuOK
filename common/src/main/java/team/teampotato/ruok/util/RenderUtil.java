package team.teampotato.ruok.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
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

    private static final int MAX_RENDERED_ENTITIES = RuOK.get().maxEntityEntities; // 最大渲染生物数量
    private static final double[] closestDistances = new double[MAX_RENDERED_ENTITIES]; // 记录最近生物的距离
    private static final Entity[] closestEntities = new Entity[MAX_RENDERED_ENTITIES]; // 记录最近生物

    public static void entityCull(Entity entity, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientWorld world = mc.world;

        if (world != null && mc.player != null) {
            double distanceToPlayer = entity.squaredDistanceTo(mc.player);


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
        for (int i = 0; i < MAX_RENDERED_ENTITIES; i++) {
            if (closestEntities[i] == entity) {
                return true;
            }
        }

        // 如果不在列表中，则尝试将其加入列表
        for (int i = 0; i < MAX_RENDERED_ENTITIES; i++) {
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
        for (int i = MAX_RENDERED_ENTITIES - 1; i > startIndex; i--) {
            closestEntities[i] = closestEntities[i - 1];
            closestDistances[i] = closestDistances[i - 1];
        }
    }
    /*
    public static void entityCull(Entity entity, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientWorld world = mc.world;

        if (world != null && mc.player != null) {
            // 如果实体距离玩家超过指定的距离，则取消渲染
            if (playerXYZInEntity(mc.player, entity, RuOK.get().entitiesDistance)) {
                ci.cancel();
            }
        }
    }

    private static boolean playerXYZInEntity(@NotNull ClientPlayerEntity player, @NotNull Entity entity, double distance) {
        double deltaX = player.getX() - entity.getX();
        double deltaY = player.getY() - entity.getY();
        double deltaZ = player.getZ() - entity.getZ();
        double distanceSquared = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
        return distanceSquared > distance * distance;
    }


     */

    private static boolean modsCullEntity(@NotNull Entity entity){
        String getClass = entity.getClass().getName();
        return
                getClass.startsWith("com.simibubi.create.content.contraptions")
                        || entity instanceof GhastEntity
                        || entity instanceof EnderDragonEntity;

    }




}
