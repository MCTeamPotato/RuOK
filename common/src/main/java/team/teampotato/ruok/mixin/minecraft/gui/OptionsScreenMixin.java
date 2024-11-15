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
import team.teampotato.ruok.gui.vanilla.screen.OtherOptions;
import team.teampotato.ruok.gui.vanilla.screen.RuOptionsScreen;
import team.teampotato.ruok.util.ClassCheck;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {


    @Shadow protected abstract Button openScreenButton(Component message, Supplier<Screen> screenSupplier);

    @Shadow @Final private Options options;

    protected OptionsScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init()V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
            shift = At.Shift.AFTER, ordinal = 9))
    private void insertCustomButton(CallbackInfo ci, @Local GridLayout.RowHelper adder) {
        // 在特定位置插入自定义按钮
        if(ClassCheck.isLoad("me.jellysquid.mods.sodium.client.SodiumClientMod")) {
            adder.addChild(this.openScreenButton(Component.translatable("ruok.options.entity.list"), () -> new OtherOptions(this)));
        } else {
            adder.addChild(this.openScreenButton(Component.translatable("ruok.options.gui.ruok"), () -> new RuOptionsScreen(this, this.options)));
        }
    }
}
