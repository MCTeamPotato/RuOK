package team.teampotato.ruok.forge.mixins.Render;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockBreakingInfo;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.*;

@Mixin(WorldRenderer.class)
public abstract class EntityRenderDispatcherMixin {
    @Shadow
    @Final
    private EntityRenderDispatcher entityRenderDispatcher;

    @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
    private void onRenderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        World world = mc.world;

        if (world != null) {
            Box box = null;
            if (mc.player != null) {
                box = mc.player.getBoundingBox().expand(RuOK.get().Render_Entities_Distance);
            }
            List<LivingEntity> LivingEntities = world.getEntitiesByClass(LivingEntity.class, box, e -> true);
            List<Entity> EntityEntities = world.getEntitiesByClass(Entity.class, box, e -> true); // 获取所有实体类型
            LivingEntities.sort(Comparator.comparingDouble(e -> e.squaredDistanceTo(mc.player)));

            if (EntityEntities.contains(entity) && EntityEntities.indexOf(entity) < RuOK.get().Max_Rendered_EntityEntities
                    ||ruOK$isWhitelisted(entity) ||
                    (!ruOK$isBlacklisted(entity)
                            && box.contains(entity.getPos())
                            && LivingEntities.contains(entity)
                            && LivingEntities.indexOf(entity) < RuOK.get().Max_Rendered_LivingEntities)
            ) {
                this.entityRenderDispatcher.getLight(entity, tickDelta);
            } else {
                ci.cancel();
            }
        }
    }
    @Unique
    private boolean ruOK$isBlacklisted(Entity entity) {
        // 检查生物是否在黑名单中
        List<String> config = RuOK.get().BlacklistedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITIES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在黑名单中 - 删除渲染
    }
    @Unique
    private boolean ruOK$isWhitelisted(Entity entity) {
        // 检查生物是否在白名单中
        List<String> config = RuOK.get().WhitelistedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITIES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在白名单中 - 添加渲染
    }



}





