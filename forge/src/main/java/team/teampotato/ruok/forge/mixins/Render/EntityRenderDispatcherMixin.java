package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.*;

@Mixin(WorldRenderer.class)
public class EntityRenderDispatcherMixin {
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
                box = mc.player.getBoundingBox().expand(RuOK.get().RenderDistance);
            }
            List<LivingEntity> sortedEntities = world.getEntitiesByClass(LivingEntity.class, box, e -> true);
            sortedEntities.sort(Comparator.comparingDouble(e -> e.squaredDistanceTo(mc.player)));

            //sortedEntities.remove(RuOK.get().BlacklistedEntities);

            if (isWhitelisted(entity) ||
                    (!isBlacklisted(entity) &&
                            box.contains(entity.getPos()) &&
                            sortedEntities.contains(entity) &&
                            sortedEntities.indexOf(entity) < RuOK.get().Max_Rendered_Entities
                    )) {
                this.entityRenderDispatcher.getLight(entity, tickDelta);
            } else {
                ci.cancel();
            }
        }
    }
    @Unique
    private boolean isBlacklisted(Entity entity) {
        // 检查生物是否在黑名单中
        List<String> config = RuOK.get().BlacklistedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在黑名单中 - 删除渲染
    }
    @Unique
    private boolean isWhitelisted(Entity entity) {
        // 检查生物是否在白名单中
        List<String> config = RuOK.get().WhitelistedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在白名单中 - 添加渲染
    }

}





