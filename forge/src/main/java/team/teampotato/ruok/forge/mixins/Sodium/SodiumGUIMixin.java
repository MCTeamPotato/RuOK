package team.teampotato.ruok.forge.mixins.Sodium;

import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.client.Pages.RuSodiumOptionPages;

import java.util.List;

@Mixin(SodiumOptionsGUI.class)
public class SodiumGUIMixin {
    @Final
    @Shadow(remap = false)
    private List<OptionPage> pages;

    @Inject(method = "<init>(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("TAIL"))
    private void addOptionPage(CallbackInfo ci){
        this.pages.add(RuSodiumOptionPages.RuOKPages());
        this.pages.add(RuSodiumOptionPages.RuOKPagesOther());
    }
}