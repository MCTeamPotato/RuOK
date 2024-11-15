package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.gui.vanilla.screen.list.BlackListScreen;
import team.teampotato.ruok.gui.vanilla.screen.list.WhiteListScreen;

public class OtherOptions extends OptionsSubScreen {

    public OtherOptions(Screen parent) {
        super(parent, Minecraft.getInstance().options, Component.translatable("ruok.options.pages.ruok.other"));
    }

    @Override
    protected void addOptions() {
        addList();
    }

    private void addList() {
        // 居中并向下调整按钮
        LinearLayout linearLayout = this.layout.addToContents(LinearLayout.horizontal().spacing(8));

        linearLayout.addChild(Button.builder(Component.translatable("ruok.options.gui.white"), (button) -> {
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(new WhiteListScreen(this, this.options));
                    }
                })  // 居中偏左，向下移动
                .build());
        linearLayout.addChild(Button.builder(Component.translatable("ruok.options.gui.black"), (button) -> {
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(new BlackListScreen(this, this.options));
                    }
                })  // 居中偏右，向下移动
                .build());

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
        } else {
            return false;
        }
    }

}
