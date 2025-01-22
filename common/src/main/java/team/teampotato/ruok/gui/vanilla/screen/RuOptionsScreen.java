package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RuOptionsScreen extends OptionsSubScreen {
    //private OptionListWidget olist;
    private final OptionInstance<?>[] ruoptions;
    protected Component title;

    public RuOptionsScreen(Component title, Screen parent, Options options, @NotNull List<OptionInstance<?>> list) {
        super(parent, options,title);
        this.ruoptions = list.toArray(OptionInstance[]::new);
    }

    @Override
    protected void addOptions() {
        if (this.list != null) {
            this.list.addSmall(ruoptions);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = this.options.guiScale().get();
        if (super.mouseClicked(mouseX, mouseY, button)) {
            if (this.options.guiScale().get() != i) {
                if (this.minecraft != null) {
                    this.minecraft.resizeDisplay();
                }
            }
            return true;
        } else return false;

    }

}
