package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.config.RuOKConfig;


public class ConfigHUDScreen extends Screen {
    private final Component DRAG_TEXT = Component.translatable("ruok.config.hud.drag");
    private final Screen parent;
    private Button buttonWidget;
    private boolean isDragging = false; // 是否在拖动
    private int dragOffsetX, dragOffsetY; // 鼠标点击与按钮的偏移量
    private int alphaValue = 0x80;


    public ConfigHUDScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        setAlphaValue(40);

        // 获取配置文件中的按钮位置，如果没有配置，则使用默认位置
        RuOKConfig config = RuOK.get();
//        int buttonX = config.GuiX != 0 ? config.GuiX : 0;
//        int buttonY = config.GuiY != 0 ? config.GuiY : 0;
        int buttonX = config.GuiX;
        int buttonY = config.GuiY;

        buttonWidget = Button.builder(DRAG_TEXT, (button) -> {
        }).bounds(buttonX, buttonY, 70, 15).build();

        this.addRenderableWidget(doneButton());
        this.addRenderableWidget(buttonWidget); // 添加按钮
    }

    @Override
    public void tick() {
        // 设置按钮的提示信息
        buttonWidget.setTooltip(Tooltip.create(Component.translatable("ruok.config.hud.button", buttonWidget.getX(), buttonWidget.getY())));
        super.tick();
    }

    @Override
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

    private Button doneButton() {
        return Button.builder(CommonComponents.GUI_DONE, (button) -> this.minecraft.setScreen(this.parent)).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (buttonWidget.isMouseOver(mouseX, mouseY)) {
            // 如果点击了按钮，开始拖动
            isDragging = true;
            dragOffsetX = (int) (mouseX - buttonWidget.getX());
            dragOffsetY = (int) (mouseY - buttonWidget.getY());
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isDragging) {
            // 释放鼠标时停止拖动
            isDragging = false;
            saveConfig(buttonWidget.getX(), buttonWidget.getY());
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging) {
            // 在拖动状态下更新按钮位置
            int newX = (int) (mouseX - dragOffsetX);
            int newY = (int) (mouseY - dragOffsetY);

            // 限制按钮位置在屏幕内
            newX = Math.max(0, Math.min(newX, this.width - buttonWidget.getWidth()));
            newY = Math.max(0, Math.min(newY, this.height - buttonWidget.getHeight()));

            buttonWidget.setX(newX);
            buttonWidget.setY(newY);
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private void saveConfig(int x, int y) {
        // 保存按钮的新位置
        RuOKConfig config = RuOK.get();
        config.GuiX = x;
        config.GuiY = y;
        RuOK.save();
    }
}
