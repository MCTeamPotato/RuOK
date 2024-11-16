package team.teampotato.ruok.neoforge.mixin;

import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.renderer.RenderStateShard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderStateShard.class)
public class RenderStateShardMixin {
    @Mutable @Final @Shadow public static RenderStateShard.OutputStateShard ITEM_ENTITY_TARGET;
    @Mutable @Final @Shadow public static RenderStateShard.OutputStateShard TRANSLUCENT_TARGET;
    @Mutable @Final @Shadow public static RenderStateShard.OutputStateShard WEATHER_TARGET;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void onClinit(CallbackInfo ci) {
        RenderTarget fb = Minecraft.getInstance().getMainRenderTarget();
        WEATHER_TARGET = new RenderStateShard.OutputStateShard("weather_target", () -> {
            if (Minecraft.useShaderTransparency()) {
                RenderTarget weather = Minecraft.getInstance().levelRenderer.getWeatherTarget();
                if (weather != null) weather.bindWrite(false);
            }
        }, () -> {
            if (Minecraft.useShaderTransparency()) fb.bindWrite(false);
        });
        ITEM_ENTITY_TARGET = new RenderStateShard.OutputStateShard("item_entity_target", () -> {
            if (Minecraft.useShaderTransparency()) {
                RenderTarget itemEntity = Minecraft.getInstance().levelRenderer.entityTarget();
                if (itemEntity != null) itemEntity.bindWrite(false);
            }
        }, () -> {
            if (Minecraft.useShaderTransparency()) fb.bindWrite(false);
        });
        TRANSLUCENT_TARGET = new RenderStateShard.OutputStateShard("translucent_target", () -> {
            if (Minecraft.useShaderTransparency()) {
                RenderTarget translucent = Minecraft.getInstance().levelRenderer.getTranslucentTarget();
                if (translucent != null) translucent.bindWrite(false);
            }

        }, () -> {
            if (Minecraft.useShaderTransparency()) fb.bindWrite(false);
        });
    }
}