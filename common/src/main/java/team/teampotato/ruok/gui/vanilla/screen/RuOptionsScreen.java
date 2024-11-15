package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.gui.vanilla.options.RuOptions;

public class RuOptionsScreen extends OptionsSubScreen {
   // private OptionListWidget list;
    private final OptionInstance<?>[] ruoptions = new RuOptions().getOptions();

    public RuOptionsScreen(Screen parent, Options options) {
        super(parent, options, Component.translatable("ruok.options.pages.ruok.main"));
    }

    @Override
    protected void addOptions() {
        this.list.addSmall(ruoptions);
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

    @Override
    protected void addFooter() {
        LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearLayout.addChild(Button.builder(Component.translatable("ruok.options.entity.list"), (button) -> {
            if (this.minecraft != null) {
                this.minecraft.options.save();
                this.minecraft.setScreen(new OtherOptions(this));  // 进入 OtherOptions 界面
            }
        }).build());
        linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.minecraft.setScreen(this.lastScreen);
        }).build());
    }

}
