package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.util.clazz.ClassManager;

public class DeBugScreen extends OptionsSubScreen {
    private final Screen parent;
    private int alphaValue = 0x80;
    private final int COLUMNS = 2;
    private final Component TRACKER_SAVE_TEXT = Component.translatable("ruok.setting.debug.tracker.save");
    public DeBugScreen(Screen parent, Component title) {
        super(parent,null,title);
        this.parent = parent;
    }


    @Override
    protected void addOptions() {
        this.setAlphaValue(50);
        Button done = Button.builder(CommonComponents.GUI_DONE, (buttonWidget) -> this.minecraft.setScreen(this.parent)).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build();;
        this.addRenderableWidget(done);
        Button save = Button.builder(TRACKER_SAVE_TEXT, (button) -> ClassManager.saveToFile()).build();
        GridLayout gridWidget = new GridLayout();
        gridWidget.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper adder = gridWidget.createRowHelper(COLUMNS);
        adder.addChild(save);

        gridWidget.arrangeElements();
        this.layout.addToContents(gridWidget);
    }

    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);
        super.render(context, mouseX, mouseY, delta);
    }
    public void renderBackground(GuiGraphics context) {
        if (minecraft.level!= null) {
            int startColor = -1072689136;
            int endColor = -804253680;
            int startRGB = startColor & 0x00FFFFFF;
            int endRGB = endColor & 0x00FFFFFF;
            startColor = (alphaValue << 24) | startRGB;
            endColor = (alphaValue << 24) | endRGB;
            context.fillGradient(0, 0, this.width, this.height, startColor, endColor);
        } else {
            this.renderMenuBackground(context);
        }
    }

    // 添加一个设置透明度的方法
    public void setAlphaValue(int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            this.alphaValue = alpha;
        }
    }


}
