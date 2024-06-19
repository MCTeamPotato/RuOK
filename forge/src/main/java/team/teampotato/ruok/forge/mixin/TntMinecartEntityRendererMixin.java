package team.teampotato.ruok.forge.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;


@Mixin(value = TntMinecartEntityRenderer.class,priority = 1200)

public class TntMinecartEntityRendererMixin {

    @Inject(method = "renderBlock(Lnet/minecraft/entity/vehicle/TntMinecartEntity;FLnet/minecraft/block/BlockState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
    private void onTntExplosionsRender(TntMinecartEntity arg, float f, BlockState arg2, MatrixStack arg3, VertexConsumerProvider arg4, int i, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }
    @Inject(method = "renderFlashingBlock", at = @At("HEAD"), cancellable = true)
    private static void onRenderFlashingBlock(BlockState blockState, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean drawFlash, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }

}
