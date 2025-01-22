package team.teampotato.ruok.mixin.minecraft;

import net.minecraft.client.resources.sounds.BiomeAmbientSoundsHandler;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;

import java.util.Optional;


@Mixin(BiomeAmbientSoundsHandler.class)
public abstract class BiomeEffectSoundPlayerMixin {
    @Shadow
    private Optional<AmbientMoodSettings> moodSettings;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V",
                    ordinal = 2
            ),
            cancellable = true
    )
    private void disableMoodSound(CallbackInfo ci) {
        if(!RuOK.get().Mood && this.moodSettings.isPresent()) {
            // 如果想彻底禁用 MOOD 声音逻辑
            this.moodSettings = Optional.empty(); // 禁用任何音效配置
            ci.cancel(); // 阻止后续逻辑执行
        }
    }

}

