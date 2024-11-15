package team.teampotato.ruok.mixin.minecraft;

import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;

@Mixin(ClientboundExplodePacket.class)
public class ExplosionS2CPacketMixin {
    @Inject(method = "handle(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V", at = @At("HEAD"), cancellable = true)
    private void onApply(ClientGamePacketListener clientGamePacketListener, CallbackInfo ci) {
        if (!RuOK.get().RenderTNTExplosions) ci.cancel();
    }
}
