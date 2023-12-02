package team.teampotato.ruok.fabric.mixins.Render;

import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.fabric.config.RuOK;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {

    @Shadow @Final @Mutable
    private static int MAX_PARTICLE_COUNT; // 去掉final，加上Mutable

    @Inject(method = "<clinit>", at = @At("RETURN")) // 在静态初始化块的末尾注入
    private static void onClinit(CallbackInfo ci) {
        MAX_PARTICLE_COUNT = RuOK.get().MaxParticleCount; // 修改为你想要的值
    }
}
