package team.teampotato.ruok.gui.vanilla.options.widget;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionListWidget extends ContainerObjectSelectionList<OptionListWidget.WidgetEntry> {
    public OptionListWidget(Minecraft client, int width, int height, int k, int l, int m) {
        super(client, width, height, l, m);
        this.centerListVertically = false;
    }

    public int addSingleOptionEntry(OptionInstance<?> option) {
        return this.addEntry(WidgetEntry.create(this.minecraft.options, this.width, option));
    }

    public void addOptionEntry(OptionInstance<?> firstOption, @Nullable OptionInstance<?> secondOption) {
        this.addEntry(WidgetEntry.create(this.minecraft.options, this.width, firstOption, secondOption));
    }

    public void addAll(OptionInstance<?>[] options) {
        for(int i = 0; i < options.length; i += 2) {
            this.addOptionEntry(options[i], i < options.length - 1 ? options[i + 1] : null);
        }

    }

    public int getRowWidth() {
        return 400;
    }

    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 32;
    }

    @Nullable
    public AbstractWidget getWidgetFor(OptionInstance<?> option) {
        Iterator<WidgetEntry> var2 = this.children().iterator();

        AbstractWidget clickableWidget;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            WidgetEntry widgetEntry = var2.next();
            clickableWidget = widgetEntry.optionsToWidgets.get(option);
        } while(clickableWidget == null);

        return clickableWidget;
    }

    public Optional<AbstractWidget> getHoveredWidget(double mouseX, double mouseY) {
        for (WidgetEntry widgetEntry : this.children()) {
            for (AbstractWidget clickableWidget : widgetEntry.widgets) {
                if (clickableWidget.isMouseOver(mouseX, mouseY)) {
                    return Optional.of(clickableWidget);
                }
            }
        }

        return Optional.empty();
    }

    protected static class WidgetEntry extends ContainerObjectSelectionList.Entry<WidgetEntry> {
        final Map<OptionInstance<?>, AbstractWidget> optionsToWidgets;
        final List<AbstractWidget> widgets;

        private WidgetEntry(@NotNull Map<OptionInstance<?>, AbstractWidget> optionsToWidgets) {
            this.optionsToWidgets = optionsToWidgets;
            this.widgets = ImmutableList.copyOf(optionsToWidgets.values());
        }

        @Contract("_, _, _ -> new")
        public static @NotNull WidgetEntry create(Options options, int width, OptionInstance<?> option) {
            return new WidgetEntry(ImmutableMap.of(option, option.createButton(options, width / 2 - 155, 0, 310)));
        }

        public static @NotNull WidgetEntry create(Options options, int width, @NotNull OptionInstance<?> firstOption, @Nullable OptionInstance<?> secondOption) {
            AbstractWidget clickableWidget = firstOption.createButton(options, width / 2 - 155, 0, 150);
            return secondOption == null ? new WidgetEntry(ImmutableMap.of(firstOption, clickableWidget)) : new WidgetEntry(ImmutableMap.of(firstOption, clickableWidget, secondOption, secondOption.createButton(options, width / 2 - 155 + 160, 0, 150)));
        }

        @Override
        public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            this.widgets.forEach((widget) -> {
                widget.setY(y);
                widget.render(context, mouseX, mouseY, tickDelta);
            });
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.widgets;
        }
        @Override
        public List<? extends NarratableEntry> narratables() {
            return this.widgets;
        }
    }
}
