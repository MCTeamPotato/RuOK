package team.teampotato.ruok.mixin.minecraft;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.util.StartTime;


@Mixin(value = Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "buildInitialScreens", at = @At("HEAD"))
    private void onInit(Minecraft.GameLoadCookie gameLoadCookie, CallbackInfoReturnable<Runnable> cir){
        StartTime.start();
    }


}