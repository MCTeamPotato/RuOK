package team.teampotato.ruok.mixin.minecraft;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;


@Mixin(ClientPacketListener.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "handleAddEntity", at = @At("HEAD"), cancellable = true)
    private void onWeatherRender(ClientboundAddEntityPacket packet, CallbackInfo ci) {
        if (RuOK.get().RenderWeather == WeatherType.CLOSE && packet.getType() == EntityType.LIGHTNING_BOLT) {
            ci.cancel();
        }
    }
}

