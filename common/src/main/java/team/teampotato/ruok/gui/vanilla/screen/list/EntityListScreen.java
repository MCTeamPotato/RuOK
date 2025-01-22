package team.teampotato.ruok.gui.vanilla.screen.list;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.util.render.EntityRender;
import team.teampotato.ruok.util.render.ParticleRender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EntityListScreen extends OptionsSubScreen {
    private EntityListWidget elist;
    private EditBox textField;
    private final List<String> lists;
    public static Collection<EntityType<?>> entityTypes = BuiltInRegistries.ENTITY_TYPE.stream().toList();
    public static EntityType<?>[] types = entityTypes.toArray(new EntityType<?>[0]);


    public EntityListScreen(Component title, Screen parent, Options options, List<String> list) {
        super(parent,options, title);
        this.font = Minecraft.getInstance().font;
        this.lists = list;
    }

    @Override
    protected void addFooter() {
        LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearLayout.addChild(this.textField);
        linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> {if (this.minecraft != null) {
            ParticleRender.reloadList();
            this.minecraft.setScreen(this.lastScreen);
        }
        }).build());
    }

    @Override
    protected void addOptions() {
        this.elist = new EntityListWidget(this,lists);
        this.textField = getComponentField();
        this.layout.addToContents(elist);
    }


    private EditBox getComponentField() {
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
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        String fieldComponent = this.textField.getValue().toLowerCase(); // 获取输入的文本，并转换为小写
        this.elist.children().clear(); // 先清空现有列表中的所有条目
        List<EntityType<?>> addedEntries = new ArrayList<>(); // 用于记录已经添加的条目
        for (EntityType<?> type : EntityListScreen.types) {
            String trID = type.getDescription().getString();
            //type.getName().getString().toLowerCase();
            String id = Objects.requireNonNull(BuiltInRegistries.ENTITY_TYPE.getKey(type)).toString();
            if ((fieldComponent.isEmpty() || trID.contains(fieldComponent)) && !addedEntries.contains(type)) {
                EntityListWidget.TypeEntry entry = this.elist.createEntry(type);
                this.elist.add(entry); // 将匹配的条目重新添加到列表中
                addedEntries.add(type); // 记录该条目已经添加
            }
            // 如果输入框为空，或者 id 或 trID 包含输入文本并且没有被添加过，则加入列表
            if ((fieldComponent.isEmpty() || id.contains(fieldComponent)) && !addedEntries.contains(type)) {
                EntityListWidget.TypeEntry entry = this.elist.createEntry(type);
                this.elist.add(entry); // 将匹配的条目重新添加到列表中
                addedEntries.add(type); // 记录该条目已经添加
            }

        }
    }
    static class EntityListWidget extends ContainerObjectSelectionList<EntityListWidget.TypeEntry> {

        public static Minecraft client = Minecraft.getInstance();
        private final List<String> list;

        public EntityListWidget(@NotNull EntityListScreen bls, List<String> list) {
            super(client,bls.width , bls.layout.getContentHeight(), bls.height, 20);
            this.list = list;
        }


        public TypeEntry createEntry(EntityType<?> type){
            return new TypeEntry(type,list);
        }

        public void add(TypeEntry typeEntry){
            this.addEntry(typeEntry);

        }
        //TODO:!!!
        public static class TypeEntry extends Entry<EntityListWidget.TypeEntry> {
            public String name;
            public Minecraft client;
            public EntityType<?> type;
            public AbstractWidget widget;
            public List<String> list;

            public TypeEntry(EntityType<?> type,List<String> list) {
                this.type = type;
                this.client = Minecraft.getInstance();
                this.list = list;
                this.name = Component.translatable(this.type.getDescriptionId()).getString();

                boolean isList = this.list.contains(Objects.requireNonNull(BuiltInRegistries.ENTITY_TYPE.getKey(type)).toString());
                this.widget = OptionInstance.createBoolean(
                        isList ? "ruok.options.gui.enable" : "ruok.options.gui.disable",
                        isList
                ).createButton(this.client.options, 60, 20, 60, this::onRenderButtonClick);
                this.refreshEntry();
            }

            private void onRenderButtonClick(boolean isEnabled) {
                String register = Integer.valueOf(BuiltInRegistries.ENTITY_TYPE.getId(type)).toString();
                if (isEnabled) {
                    this.list.add(register);
                } else {
                    this.list.remove(register);
                }
                RuOK.save();
                EntityRender.reloadList();
                this.refreshEntry();
            }

            public void refreshEntry() {
                this.widget.setMessage(this.list.contains(Objects.requireNonNull(BuiltInRegistries.ENTITY_TYPE.getKey(type).toString()))
                        ? Component.translatable("ruok.options.gui.enable")
                        : Component.translatable("ruok.options.gui.disable"));
            }
            @Override
            public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
                int textYPosition = y + entryHeight / 2 - 9 / 2;
                context.drawString(client.font, this.name,x-20, textYPosition, 16777215, false);
                this.widget.setX(x + 160);
                this.widget.setY(y);
                this.widget.render(context, mouseX, mouseY, tickDelta);
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return ImmutableList.of(this.widget);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                return List.of();
            }
        }

    }


}
