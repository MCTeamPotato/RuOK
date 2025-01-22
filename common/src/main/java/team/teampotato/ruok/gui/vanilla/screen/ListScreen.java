package team.teampotato.ruok.gui.vanilla.screen;

import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.SpacerElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.screen.list.EntityListScreen;
import team.teampotato.ruok.gui.vanilla.screen.list.ParticleListScreen;

import java.util.Objects;
import java.util.function.Supplier;
@SuppressWarnings("ConstantConditions")
public class ListScreen extends OptionsSubScreen {
    private final Component WHITE_ENTITY_TEXT = Component.translatable("ruok.options.gui.white.entity");
    private final Component BLACK_ENTITY_TEXT = Component.translatable("ruok.options.gui.black.entity");
    private final Component WHITE_PARTICLE_TEXT = Component.translatable("ruok.options.gui.white.particle");
    private final Component BLACK_PARTICLE_TEXT = Component.translatable("ruok.options.gui.black.particle");
    private final int COLUMNS = 2;
    private final Screen parent;
    private final Options settings;
    public ListScreen(Component title,Screen parent, Options gameOptions) {
        super(parent,gameOptions,title);
        this.parent = parent;
        this.settings = gameOptions;
    }

    @Override
    protected void addOptions() {
        GridLayout gridWidget = new GridLayout();
        gridWidget.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper adder = gridWidget.createRowHelper(COLUMNS);
        adder.addChild(this.createButton(WHITE_PARTICLE_TEXT,()-> new ParticleListScreen(WHITE_PARTICLE_TEXT,this,this.settings, RuOK.get().WhiteListedParticle)));
        adder.addChild(this.createButton(BLACK_PARTICLE_TEXT, () -> new ParticleListScreen(BLACK_PARTICLE_TEXT,this, this.settings,RuOK.get().BlackListedParticle)));
        adder.addChild(this.createButton(WHITE_ENTITY_TEXT, () -> new EntityListScreen(WHITE_ENTITY_TEXT,this, this.settings,RuOK.get().whiteListedEntities)));
        adder.addChild(this.createButton(BLACK_ENTITY_TEXT, () -> new EntityListScreen(BLACK_ENTITY_TEXT,this, this.settings,RuOK.get().blackListedEntities)));

        int but = 5-1;
        for (int i = 0; i < but; i++) {
            adder.addChild(SpacerElement.height(26), COLUMNS);//空,渲染
        }

        gridWidget.arrangeElements();
        this.layout.addToContents(gridWidget);
    }

    private Button createButton(Component message, Supplier<Screen> screenSupplier) {
        return Button.builder(message, (button) -> Objects.requireNonNull(this.minecraft).setScreen(screenSupplier.get())).build();
    }
}
