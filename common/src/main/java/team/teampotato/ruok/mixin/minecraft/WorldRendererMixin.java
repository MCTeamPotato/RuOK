package team.teampotato.ruok.mixin.minecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.util.Render;


@Mixin(value = LevelRenderer.class, priority = 1200)
public abstract class WorldRendererMixin {
    @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
    private void onRender(Entity entity, double d, double e, double f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, CallbackInfo ci) {
        Render.entityCull(entity,ci);
    }
}







