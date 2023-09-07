package team.teampotato.ruok.forge.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.RuOK;

import java.util.Comparator;
import java.util.List;


@Mixin(WorldRenderer.class)
public class EntityRenderDispatcherMixin {
    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;

    @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
    private void onRenderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        World world = mc.world;

        if (world != null) {
            Box box = null; // 创建一个以玩家为中心，边长为视距*8米的立方体范围盒子
            if (mc.player != null) {
                box = mc.player.getBoundingBox().expand(RuOK.get().RenderDistance);
            }
            List<LivingEntity> sortedEntities = world.getEntitiesByClass(LivingEntity.class, box, e -> true); // 获取范围内的所有生物实体
            sortedEntities.sort(Comparator.comparingDouble(e -> e.squaredDistanceTo(mc.player))); // 按照距离玩家的远近排序生物实体列表

            //sortedEntities.size() + "个生物";

            if (box.contains(entity.getPos()) && sortedEntities.contains(entity) && sortedEntities.indexOf(entity) < RuOK.get().Max_Rendered_Entities) { // 判断当前实体是否在范围盒子内，范围内，和前10个
                this.entityRenderDispatcher.getLight(entity, tickDelta); // 渲染当前实体
            } else {
                ci.cancel(); // 取消渲染当前实体
            }



        }
    }

}
