package team.teampotato.ruok.mixin.sodium;

import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.gui.sodium.Options;

import java.util.List;

@Mixin(SodiumOptionsGUI.class)
public class SodiumGUIMixin {

    @Shadow(remap = false) @Final private List<OptionPage> pages;

    @Inject(method = "<init>(Lnet/minecraft/client/gui/screens/Screen;)V", at = @At("TAIL"))
    private void addOptionPage(CallbackInfo ci){
        this.pages.add(Options.getMainGroups());
        this.pages.add(Options.getOtherGroups());
        this.pages.add(Options.getHudGroups());
    }
}