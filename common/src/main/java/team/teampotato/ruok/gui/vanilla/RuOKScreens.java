package team.teampotato.ruok.gui.vanilla;

import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.options.RuOKOptionsStorage;
import team.teampotato.ruok.gui.vanilla.screen.ConfigHUDScreen;
import team.teampotato.ruok.gui.vanilla.screen.DeBugScreen;
import team.teampotato.ruok.gui.vanilla.screen.ListScreen;
import team.teampotato.ruok.gui.vanilla.screen.RuOptionsScreen;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class RuOKScreens extends OptionsSubScreen {
    private final Component NORMAL_SETTING_TEXT = Component.translatable("ruok.setting.normal");
    private final Component OTHER_SETTING_TEXT = Component.translatable("ruok.setting.other");
    private final Component HUD_SETTING_TEXT = Component.translatable("ruok.setting.hud");
    private final Component LIST_SETTING_TEXT = Component.translatable("ruok.setting.list");
    private final Component HUD_CONFIG_TEXT = Component.translatable("ruok.config.hud");
    private final Component DEBUG_HUD_TEXT = Component.translatable("ruok.setting.debug");
    private final int COLUMNS = 2;
    private final Screen parent;
    private final Options settings;
    private int alphaValue = 0x80;
    public RuOKScreens(Screen parent, Options gameOptions) {
        super(parent,gameOptions,Component.translatable("ruok.options.pages.ruok.main"));
        this.parent = parent;
        this.settings = gameOptions;
    }

    @Override
    protected void addOptions() {
        setAlphaValue(40);
        GridLayout gridWidget = new GridLayout();
        gridWidget.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper adder = gridWidget.createRowHelper(COLUMNS);
        adder.addChild(this.createButton(NORMAL_SETTING_TEXT,() -> new RuOptionsScreen(NORMAL_SETTING_TEXT,this,this.settings, RuOKOptionsStorage.getMainOptions())));
        adder.addChild(this.createButton(OTHER_SETTING_TEXT, () -> new RuOptionsScreen(OTHER_SETTING_TEXT,this,this.settings, RuOKOptionsStorage.getOtherOptions())));
        adder.addChild(this.createButton(HUD_SETTING_TEXT, () -> new RuOptionsScreen(HUD_SETTING_TEXT,this,this.settings, RuOKOptionsStorage.getHudOptions())));
        adder.addChild(this.createButton(LIST_SETTING_TEXT, () -> new ListScreen(LIST_SETTING_TEXT,this,this.settings)));
        adder.addChild(this.createButton(HUD_CONFIG_TEXT, () -> new ConfigHUDScreen(HUD_CONFIG_TEXT,this)));

        if(RuOK.get().DeBug)adder.addChild(this.createButton(DEBUG_HUD_TEXT, () -> new DeBugScreen(this,DEBUG_HUD_TEXT)));
        gridWidget.arrangeElements();
        this.layout.addToContents(gridWidget);
    }

    private void setAdder(GridLayout.@NotNull RowHelper adder) {
        adder.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> this.minecraft.setScreen(this.parent)).width(200).build(), COLUMNS, adder.newCellSettings().paddingTop(6));
    }
    private Button doneButton() {
        return Button.builder(CommonComponents.GUI_DONE, (button) -> this.minecraft.setScreen(this.parent)).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build();  // 偏向右边
    }
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);
        super.render(context, mouseX, mouseY, delta);
    }
    private Button createButton(Component message, Supplier<Screen> screenSupplier) {
        return Button.builder(message, (button) -> Objects.requireNonNull(this.minecraft).setScreen(screenSupplier.get())).build();
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
            renderMenuBackground(context);
        }
    }

    // 添加一个设置透明度的方法
    public void setAlphaValue(int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            this.alphaValue = alpha;
        }
    }

}
