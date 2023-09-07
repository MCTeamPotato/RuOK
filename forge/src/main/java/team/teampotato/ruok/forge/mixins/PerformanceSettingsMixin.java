package team.teampotato.ruok.forge.mixins;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.client.MCQuality;

@Mixin(SodiumGameOptions.PerformanceSettings.class)
public class PerformanceSettingsMixin {
    @Inject(
            remap = false,
            method = "<init>",
            at = @At("RETURN")
    )
    private void onPerformanceSettingsInit(CallbackInfo ci) {
        MCQuality.CustomGraphicsQuality = true;
        // 在 PerformanceSettings 的构造函数被调用后执行你的代码
    }
}
