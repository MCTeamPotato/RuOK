package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(LightingProvider.class)
public class LightingProviderMixin {
    @Inject(
            method = "doLightUpdates",
            at = @At("HEAD"),
            cancellable = true
    )
    public void doLightUpdates(int i, boolean doSkylight, boolean skipEdgeLightPropagation, CallbackInfoReturnable<Integer> cir) {
        if (!RuOK.get().RenderLighting) {
            cir.cancel();
            cir.setReturnValue(10);
        }

    }

}