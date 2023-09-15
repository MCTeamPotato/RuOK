package team.teampotato.ruok.forge.mixins.Chunk;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class ChunkRenderDispatcherMixin {

    @Unique
    private final long ruOK$TempRenderDistanceChunks = MinecraftClient.getInstance().options.getViewDistance().getValue();//缓存值，只记录一次

    @Inject(method = "updateChunks", at = @At("HEAD"))
    private void onUpdateChunks(Camera camera, CallbackInfo ci) {

    }
}
