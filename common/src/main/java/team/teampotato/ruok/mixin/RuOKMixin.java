package team.teampotato.ruok.mixin;

import org.spongepowered.asm.mixin.Mixin;


import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.RuOKMod;

@Mixin(TitleScreen.class)
public class RuOKMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        RuOKMod.LOGGER.info("This line is printed by an example mod mixin!");
    }
}
