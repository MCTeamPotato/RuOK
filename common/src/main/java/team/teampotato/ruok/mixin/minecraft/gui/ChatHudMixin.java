package team.teampotato.ruok.mixin.minecraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.util.ChatFix;

@Mixin(ChatComponent.class)
public class ChatHudMixin {
    @Shadow @Final private Minecraft minecraft;

    @ModifyArg(method = "render", index = 1, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V" , ordinal = 0))
    private float offsetY(float y) {
        if (RuOK.get().chatFix) return y - ChatFix.getOffset(this.minecraft);
        else return y;
    }
    @ModifyConstant(method = "screenToChatY", constant = @Constant(doubleValue = 40.0))
    private double textBottomOffset(double original) {
        if (RuOK.get().chatFix) return original + ChatFix.getOffset(this.minecraft);
        else return original;
    }
}