package team.teampotato.ruok.gui.sodium;

import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.gui.sodium.storage.OptionsStorage;
import team.teampotato.ruok.gui.sodium.storage.RuOKGameOptions;
import team.teampotato.ruok.gui.vanilla.screen.ConfigHUDScreen;

public class OtherOptions {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Component HUD_CONFIG_TEXT = Component.translatable("ruok.config.hud");
    public static OptionImpl<RuOKGameOptions, Boolean> getHUDConfigScreen() {
        return OptionImpl.createBuilder(Boolean.class, OptionsStorage.getOptionStorage())
                .setName(Component.translatable("ruok.config.hud"))
                .setTooltip(Component.translatable("ruok.config.hud.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options,bool) -> mc.setScreen(new ConfigHUDScreen(HUD_CONFIG_TEXT,mc.screen)),
                        (getter) -> false
                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

    }
}
