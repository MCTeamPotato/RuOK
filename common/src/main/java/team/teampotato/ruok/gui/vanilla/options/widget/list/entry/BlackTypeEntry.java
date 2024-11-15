package team.teampotato.ruok.gui.vanilla.options.widget.list.entry;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.config.RuOK;

import java.util.List;

public class BlackTypeEntry extends ContainerObjectSelectionList.Entry<BlackTypeEntry> {
    public Minecraft client;
    public EntityType<?> type;
    public String name;
    public AbstractWidget render;

    public BlackTypeEntry(EntityType<?> type) {
        this.type = type;
        this.name = Component.translatable(this.type.getDescriptionId()).getString();
        this.client = Minecraft.getInstance();

        boolean isBlacklisted = RuOK.get().blackListedEntities.contains(RuOKMod.getRegisterName(this.type));

        this.render = OptionInstance.createBoolean(
                isBlacklisted ? "ruok.options.gui.enable" : "ruok.options.gui.disable",
                isBlacklisted
        ).createButton(this.client.options, 60, 20, 60, this::onRenderButtonClick);

        this.refreshEntry();
    }

    private void onRenderButtonClick(boolean isEnabled) {
        String register = RuOKMod.getRegisterName(this.type);
        if (isEnabled) {
            RuOK.get().blackListedEntities.add(register);
        } else {
            RuOK.get().blackListedEntities.remove(register);
        }
        RuOK.save();
        this.refreshEntry();
    }

    public void refreshEntry() {
        this.render.setMessage(RuOK.get().blackListedEntities.contains(RuOKMod.getRegisterName(this.type))
                ? Component.translatable("ruok.options.gui.enable")
                : Component.translatable("ruok.options.gui.disable"));
    }

    @Override
    public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        Minecraft m = Minecraft.getInstance();
        int textYPosition = y + entryHeight / 2 - 9 / 2;

        context.drawString(m.font, this.type.getDescription(), x + 20, textYPosition, 16777215, false);

        this.render.setX(x + 160);
        this.render.setY(y);
        this.render.render(context, mouseX, mouseY, tickDelta);
    }

    @Override
    public List<? extends NarratableEntry> narratables() {
        return ImmutableList.of();
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return ImmutableList.of(this.render);
    }
}
