package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.world.GeneratorOptionsHolder;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.SaveLoading;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.teampotato.ruok.RuOKMod;

import java.util.concurrent.CompletableFuture;

@Mixin(CreateWorldScreen.class)
public abstract class TestCreateWorldScreenMixin {

    // 注入到create方法中，在每个步骤之前打印日志信息
    @Inject(method = "create*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;runTasks(Ljava/util/function/BooleanSupplier;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectBeforeRunTasks(MinecraftClient client, Screen parent, CallbackInfo ci, ResourcePackManager packrepository, SaveLoading.ServerConfig worldloader$initconfig, CompletableFuture<GeneratorOptionsHolder> completablefuture) {
        // 打印"正在加载数据包"
        RuOKMod.LOGGER.info("Loading data packs");
    }

    @Inject(method = "create*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectBeforeSetScreen(MinecraftClient client, Screen parent, CallbackInfo ci, ResourcePackManager packrepository, SaveLoading.ServerConfig worldloader$initconfig, CompletableFuture<GeneratorOptionsHolder> completablefuture) {
        // 打印"正在创建世界界面"
        RuOKMod.LOGGER.info("Creating world screen");
    }

    @Inject(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;applyDataPacks(Lnet/minecraft/resource/ResourcePackManager;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectBeforeApplyDataPacks(MinecraftClient client, Screen parent, CallbackInfo ci, ResourcePackManager packrepository, SaveLoading.ServerConfig worldloader$initconfig, CompletableFuture<GeneratorOptionsHolder> completablefuture) {
        // 打印"正在验证数据包"
        RuOKMod.LOGGER.info("Validating data packs");
    }
}
