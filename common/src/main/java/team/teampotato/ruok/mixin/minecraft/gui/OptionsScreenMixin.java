package team.teampotato.ruok.mixin.minecraft.gui;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.RuOKScreens;
import team.teampotato.ruok.gui.vanilla.screen.ListScreen;
import team.teampotato.ruok.util.ModLoadState;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Shadow protected abstract Button openScreenButton(Component message, Supplier<Screen> screenSupplier);

    @Shadow @Final
    public Options options;

    protected OptionsScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init()V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
            shift = At.Shift.AFTER, ordinal = 9)
    )
    private void onInit(CallbackInfo ci, @Local GridLayout.RowHelper adder) {
        // 如果玩家加载了Sodium模组，且不希望使用VanillaGui，显示ListScreen
        if (ModLoadState.isSodium() && !RuOK.get().UseVanillaGui) {
            adder.addChild(this.openScreenButton(Component.translatable("ruok.options.entity.list"), () -> new ListScreen(Component.translatable("ruok.setting.list"), this, this.options)));
        } else {
            // 默认或Sodium加载且希望使用RuOK界面，显示RuOKScreens
            adder.addChild(this.openScreenButton(Component.translatable("ruok.options.gui.ruok"), () -> new RuOKScreens(this, this.options)));
        }
    }

}
