package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
    private void onWeatherRender(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if (RuOK.get().RenderWeather == RuOK.weather.CLOSE && packet.getEntityTypeId() == EntityType.LIGHTNING_BOLT) {
            ci.cancel();
        }
    }
}

