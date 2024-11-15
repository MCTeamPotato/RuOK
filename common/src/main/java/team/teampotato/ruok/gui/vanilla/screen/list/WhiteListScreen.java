package team.teampotato.ruok.gui.vanilla.screen.list;

import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import team.teampotato.ruok.gui.vanilla.options.widget.list.WhiteEntityListWidget;
import team.teampotato.ruok.gui.vanilla.options.widget.list.entry.WhiteTypeEntry;

public class WhiteListScreen extends OptionsSubScreen {

    public WhiteEntityListWidget wlist;

    public EditBox textField;

    public WhiteListScreen(Screen parent, Options options) {
        super(parent,options, Component.translatable("ruok.options.gui.white"));
    }

    @Override
    protected void addOptions() {
        this.wlist = new WhiteEntityListWidget(this,this.minecraft);
        this.textField = getTextField();
        this.layout.addToContents(this.wlist);
    }



    private EditBox getTextField() {
        return this.addRenderableWidget(
                new EditBox(
                        this.font,
                        this.width / 2 - 155,
                        this.height - 29,
                        150,
                        20,
                        Component.empty()
                )
        );
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        this.wlist.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(this.font, this.title, this.width / 2, 8, 16777215);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        String fieldText = this.textField.getValue().toLowerCase(); // 获取输入的文本，并转换为小写
        this.wlist.children().clear(); // 先清空现有列表中的所有条目

        // 根据输入的文本进行筛选并重新添加符合条件的条目
        for (EntityType<?> type : WhiteEntityListWidget.types) {
            String entryName = type.getDescription().getString().toLowerCase();
            if (fieldText.isEmpty() || entryName.contains(fieldText)) {
                WhiteTypeEntry entry = this.wlist.createEntry(type);
                this.wlist.add(entry); // 将匹配的条目重新添加到列表中
            }
        }
    }

    @Override
    protected void addFooter() {
        LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearLayout.addChild(this.textField);
        linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.minecraft.setScreen(this.lastScreen);
        }).build());
    }

}