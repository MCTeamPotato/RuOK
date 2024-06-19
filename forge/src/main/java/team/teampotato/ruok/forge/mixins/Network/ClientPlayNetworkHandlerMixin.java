<<<<<<<< Updated upstream:forge/src/main/java/team/teampotato/ruok/forge/mixins/Network/ClientPlayNetworkHandlerMixin.java
package team.teampotato.ruok.forge.mixins.Network;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
    private void onWeatherRender(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if (!RuOK.get().WeatherNetwork && packet.getEntityTypeId() == EntityType.LIGHTNING_BOLT) {
            ci.cancel();
        }
    }
}


========
package team.teampotato.ruok.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.util.Weather;


@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
    private void onWeatherRender(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if (RuOK.get().RenderWeather == Weather.CLOSE && packet.getEntityTypeId() == EntityType.LIGHTNING_BOLT) {
            ci.cancel();
        }
    }
}

>>>>>>>> Stashed changes:common/src/main/java/team/teampotato/ruok/mixin/ClientPlayNetworkHandlerMixin.java
