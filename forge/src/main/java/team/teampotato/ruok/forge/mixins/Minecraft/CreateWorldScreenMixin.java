package team.teampotato.ruok.forge.mixins.Minecraft;

import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.resource.ResourcePackManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.teampotato.ruok.forge.config.RuOK;


import java.util.List;

@Mixin(value = CreateWorldScreen.class,priority = 1900)
public abstract class CreateWorldScreenMixin {

    // 使用@Redirect注解将applyDataPacks方法中的条件判断重定向到一个返回true的方法
    @Redirect(method = "applyDataPacks", at = @At(value = "INVOKE", target = "Ljava/util/List;equals(Ljava/lang/Object;)Z"))
    private boolean redirectEquals(List<?> list, Object obj, ResourcePackManager dataPackManager) {
        return !RuOK.get().DataPackVerify;
    }

}
