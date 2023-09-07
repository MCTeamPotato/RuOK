package team.teampotato.ruok.forge.mixins;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(MinecraftClient.class)
public interface FrameRateTracker {
    @Accessor("currentFps")
    static int getFPS() {
        return 0;
    }
}