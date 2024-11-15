package team.teampotato.ruok.gui.vanilla.options.widget.list.entry;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.screen.list.BlackListScreen;

import java.util.Collection;
import java.util.List;

public class WhiteTypeEntry extends ContainerObjectSelectionList.Entry<WhiteTypeEntry> {
    public Minecraft client;
    public EntityType<?> type;
    public String name;
    public AbstractWidget render;

    public WhiteTypeEntry(EntityType<?> type) {
        this.type = type;
        this.name = Component.translatable(this.type.getDescriptionId()).getString();
        this.client = Minecraft.getInstance();

        boolean isWhitelisted = RuOK.get().whiteListedEntities.contains(RuOKMod.getRegisterName(this.type));

        this.render = OptionInstance.createBoolean(
                isWhitelisted ? "ruok.options.gui.enable" : "ruok.options.gui.disable",
                isWhitelisted
        ).createButton(this.client.options, 60, 20, 60, this::onRenderButtonClick);

        this.refreshEntry();
    }

    private void onRenderButtonClick(boolean isEnabled) {
        String register = RuOKMod.getRegisterName(this.type);
        if (isEnabled) {
            RuOK.get().whiteListedEntities.add(register);
        } else {
            RuOK.get().whiteListedEntities.remove(register);
        }
        RuOK.save();
        this.refreshEntry();
    }

    public void refreshEntry() {
        this.render.setMessage(RuOK.get().whiteListedEntities.contains(RuOKMod.getRegisterName(this.type))
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
    public List<? extends GuiEventListener> children() {
        return ImmutableList.of(this.render);
    }
    @Override
    public List<? extends NarratableEntry> narratables() {
        return ImmutableList.of();
    }

    public class BlackEntityListWidget extends ContainerObjectSelectionList<BlackTypeEntry> {

        public static Minecraft client;
        final BlackListScreen config;

        public static Collection<EntityType<?>> entityTypes = BuiltInRegistries.ENTITY_TYPE.stream().toList();
        public static EntityType<?>[] types = entityTypes.toArray(new EntityType<?>[0]);

        public BlackEntityListWidget(@NotNull BlackListScreen cfgs, Minecraft Minecraft) {
            super(Minecraft,cfgs.width , cfgs.height, cfgs.height - 32, 20);
            this.config = cfgs;
            client = Minecraft.getInstance();

            for(EntityType<?> type:types){
                this.addEntry(new BlackTypeEntry(type));
            }
        }

        public static Minecraft getClient() {
            return client;
        }

        public BlackTypeEntry createEntry(EntityType<?> type){
            return new BlackTypeEntry(type);
        }

        public void add(BlackTypeEntry blackTypeEntry){
            this.addEntry(blackTypeEntry);

        }

        public boolean has(BlackTypeEntry entry){
            for(BlackTypeEntry etr:this.children()){
                if(etr.name.equalsIgnoreCase(entry.name)){
                    return true;
                }
            }
            return false;
        }


    }
}
