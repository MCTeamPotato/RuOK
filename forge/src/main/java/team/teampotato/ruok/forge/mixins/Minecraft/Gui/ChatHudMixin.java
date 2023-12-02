package team.teampotato.ruok.forge.mixins.Minecraft.Gui;

import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @ModifyConstant(
            method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V",
            constant = @Constant(intValue = 100),
            expect = 2
    )
    public int onAddMessage(int constant) {
        return 16384;
    }
}