package team.teampotato.ruok.fabric.mixins.Network;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.fabric.config.RuOK;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
    private void onWeatherRender(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if (!RuOK.get().WeatherNetwork && packet.getEntityTypeId() == EntityType.LIGHTNING_BOLT) {
            ci.cancel();
        }
    }
}


