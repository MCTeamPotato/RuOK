package team.teampotato.ruok.mixin.sodium;

import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.page.SodiumOptions;

import java.util.List;

@Mixin(SodiumOptionsGUI.class)
public class SodiumGUIMixin {

    @Shadow @Final private List<OptionPage> pages;

    @Inject(method = "<init>(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("TAIL"))
    private void addOptionPage(CallbackInfo ci){
        try {
            this.pages.add(SodiumOptions.RuOKPages());
            this.pages.add(SodiumOptions.RuOKPagesOther());
        } catch (Exception e) {
            RuOKMod.LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}