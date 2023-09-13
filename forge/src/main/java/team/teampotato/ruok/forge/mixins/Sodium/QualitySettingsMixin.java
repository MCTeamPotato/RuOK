package team.teampotato.ruok.forge.mixins.Sodium;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.MCQuality;

@Mixin(SodiumGameOptions.QualitySettings.class)

public class QualitySettingsMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onQualitySettingsInit(CallbackInfo ci) {
       MCQuality.CustomGraphicsQuality = true;
    }
}
