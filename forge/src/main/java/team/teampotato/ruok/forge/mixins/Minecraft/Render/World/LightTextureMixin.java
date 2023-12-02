package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(LightmapTextureManager.class)

public class LightTextureMixin {
    @Shadow private boolean dirty;

    @Inject(
            method = "tick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onTick(CallbackInfo ci) {
        if(!RuOK.get().RenderLighting) {
            this.dirty = false;
            ci.cancel();
        } else this.dirty = true;
    }
}
