package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import net.minecraft.client.particle.ExplosionEmitterParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ExplosionEmitterParticle.Factory.class)
public class ExplosionEmitterParticleMixin {
    @Inject(
            method = "createParticle*",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onCreateParticle(CallbackInfoReturnable<ExplosionEmitterParticle> ci) { // 修改参数类型为CallbackInfoReturnable
        if (!RuOK.get().RenderTNTExplosions) {
            ci.cancel(); // 取消原方法的执行
            ci.setReturnValue(null); // 设置返回值为null，避免空指针异常
        }
    }
}
