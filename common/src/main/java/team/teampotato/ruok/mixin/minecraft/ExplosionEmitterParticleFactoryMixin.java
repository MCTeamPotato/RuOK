package team.teampotato.ruok.mixin.minecraft;

import net.minecraft.client.particle.HugeExplosionSeedParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.config.RuOK;

@Mixin(HugeExplosionSeedParticle.Provider.class)
public class ExplosionEmitterParticleFactoryMixin {
    @Inject(method = "createParticle*", at = @At("HEAD"), cancellable = true)
    public void onCreateParticle(CallbackInfoReturnable<HugeExplosionSeedParticle> ci) { // 修改参数类型为CallbackInfoReturnable
        if (!RuOK.get().RenderTNTExplosions) {
            ci.cancel(); // 取消原方法的执行
            ci.setReturnValue(null);
        }
    }
}
