package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.*;

import static team.teampotato.ruok.forge.util.Render.RenderUtil.isBlacklisted;
import static team.teampotato.ruok.forge.util.Render.RenderUtil.isWhitelisted;

@Mixin(value = WorldRenderer.class, priority = 1200)
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
            List<LightningEntity> LightningEntity = world.getEntitiesByClass(LightningEntity.class, box, e -> true);
            LivingEntities.sort(Comparator.comparingDouble(e -> e.squaredDistanceTo(mc.player)));

            if(
                    (box!=null
                    && EntityEntities.contains(entity)
                    && EntityEntities.indexOf(entity) < RuOK.get().Max_Rendered_EntityEntities)
                    || (box!=null
                    && box.contains(entity.getPos())
                    && LivingEntities.contains(entity)
                    && LivingEntities.indexOf(entity) < RuOK.get().Max_Rendered_LivingEntities)
                    || (box!=null
                    && box.contains(entity.getPos())
                    && LightningEntity.contains(entity)
                    && LightningEntity.indexOf(entity) < RuOK.get().Max_Rendered_EntityEntities)
                    || isWhitelisted(entity)
                    || !isBlacklisted(entity)
            ) {
                this.entityRenderDispatcher.getLight(entity, tickDelta);
            } else {
                ci.cancel();
            }
        }
    }



}





