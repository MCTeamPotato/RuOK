package team.teampotato.ruok.mixin.minecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;


@Mixin(value = TntMinecartRenderer.class,priority = 1200)

public class TntMinecartEntityRendererMixin {

    @Inject(method = "renderMinecartContents(Lnet/minecraft/world/entity/vehicle/MinecartTNT;FLnet/minecraft/world/level/block/state/BlockState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    private void onTntExplosionsRender(MinecartTNT minecartTNT, float f, BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }
    @Inject(method = "renderWhiteSolidBlock", at = @At("HEAD"), cancellable = true)
    private static void onRenderFlashingBlock(BlockRenderDispatcher blockRenderDispatcher, BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, boolean bl, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }

}
