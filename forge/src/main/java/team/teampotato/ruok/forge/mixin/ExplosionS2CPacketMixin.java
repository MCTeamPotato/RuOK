package team.teampotato.ruok.forge.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ExplosionS2CPacket.class)

public class ExplosionS2CPacketMixin {
    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"), cancellable = true)
    private void onApply(ClientPlayPacketListener arg, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }
}
