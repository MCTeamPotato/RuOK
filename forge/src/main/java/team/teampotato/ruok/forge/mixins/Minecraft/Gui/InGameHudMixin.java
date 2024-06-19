package team.teampotato.ruok.forge.mixins.Minecraft.Render.Gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.util.Render.RenderUtil;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        MinecraftClient instance = MinecraftClient.getInstance();
        if (instance.player != null && !RuOK.get().GuiCulling && instance.player.world.isClient) return;
        RenderUtil.RenderCullGui(matrices,instance);
    }
}
