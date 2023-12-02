package team.teampotato.ruok.forge.mixins.Minecraft.Accessor;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
    @Accessor("currentFps")
    static int getFPS() {
        return 0;
    }
}