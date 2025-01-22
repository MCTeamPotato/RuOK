package team.teampotato.ruok.gui.sodium;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.gui.base.Base;
import team.teampotato.ruok.gui.base.BaseManager;
import team.teampotato.ruok.gui.base.BaseUtil;
import team.teampotato.ruok.gui.base.compat.SodiumCompat;
import team.teampotato.ruok.gui.sodium.storage.OptionsStorage;
import team.teampotato.ruok.gui.sodium.storage.RuOKGameOptions;
import team.teampotato.ruok.gui.vanilla.mode.BlockBreakParticleType;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class Options {
    private static final List<OptionGroup> hudGroup = new ArrayList<>();
    private static final List<OptionGroup> otherGroup = new ArrayList<>();
    private static final List<OptionGroup> mainGroup = new ArrayList<>();
    private static final OptionGroup.Builder mainBuilder = OptionGroup.createBuilder();
    private static final OptionGroup.Builder otherBuilder = OptionGroup.createBuilder();
    private static final OptionGroup.Builder hudBuilder = OptionGroup.createBuilder();
    private static final List<Base<RuOKGameOptions, ?>> adaptedBases = SodiumCompat.adaptBaseList(BaseManager.getBinds());

    public static List<OptionGroup> getHudGroup() {
        return hudGroup;
    }

    public static List<OptionGroup> getMainGroup() {
        return mainGroup;
    }

    @Contract(" -> new")
    public static @NotNull OptionPage getMainGroups() {
        if (Options.getMainGroup().isEmpty()) Options.initMainGroup();
        return new OptionPage(Component.translatable("ruok.options.pages.ruok.main"), ImmutableList.copyOf(Options.getMainGroup()));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage getOtherGroups() {
        if (Options.getOtherGroup().isEmpty()) Options.initOtherGroup();
        return new OptionPage(Component.translatable("ruok.options.pages.ruok.other"), ImmutableList.copyOf(Options.getOtherGroup()));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage getHudGroups() {
        if (Options.getHudGroup().isEmpty()) Options.initHUDGroup();
        return new OptionPage(Component.translatable("ruok.options.pages.ruok.hud"), ImmutableList.copyOf(Options.getHudGroup()));
    }

    public static List<OptionGroup> getOtherGroup() {
        return otherGroup;
    }

    public static void initGroup() {
        Options.initMainGroup(); // 注册Main界面UI
        Options.initOtherGroup(); // 注册Other界面UI
        Options.initHUDGroup();
    }

    private static void initHUDGroup() {
        processGroup(BaseUtil.Group.HUD, hudBuilder);
        hudBuilder.add(OtherOptions.getHUDConfigScreen());
        Options.getHudGroup().add(hudBuilder.build());
    }

    private static void initMainGroup() {
        processGroup(BaseUtil.Group.MAIN, mainBuilder);
        Options.getMainGroup().add(mainBuilder.build());
    }

    private static void initOtherGroup() {
        processGroup(BaseUtil.Group.OTHER, otherBuilder);
        Options.getOtherGroup().add(otherBuilder.build());
    }

    private static void processGroup(BaseUtil.Group group, OptionGroup.Builder builder) {
        // 将 adaptedBases 分为两组：有 rank 和无 rank
        List<Base<RuOKGameOptions, ?>> rankedBases = new ArrayList<>();
        List<Base<RuOKGameOptions, ?>> unrankedBases = new ArrayList<>();

        for (Base<RuOKGameOptions, ?> base : adaptedBases) {
            if (base.getGroup() == group) {
                if (base.getRank() > 0) {
                    rankedBases.add(base); // 有效 rank
                } else {
                    unrankedBases.add(base); // 无 rank 或 rank <= 0
                }
            }
        }

        // 对 rankedBases 按 rank 排序
        rankedBases.sort(Comparator.comparingInt(Base::getRank));

        // 对 unrankedBases 随机排序
        Collections.shuffle(unrankedBases);

        // 合并排序后的 Base 列表
        List<Base<RuOKGameOptions, ?>> sortedBases = new ArrayList<>();
        sortedBases.addAll(rankedBases);
        sortedBases.addAll(unrankedBases);

        // 按排序后的顺序处理 Base 对象
        for (Base<RuOKGameOptions, ?> base : sortedBases) {
            switch (base.getType()) {
                case BOOLEAN -> processBoolean((Base<RuOKGameOptions, Boolean>) base, builder);
                case INT -> processInteger((Base<RuOKGameOptions, Integer>) base, builder);
                case ENUM -> processEnum(base, builder);
            }
        }
    }



    private static void processBoolean(Base<RuOKGameOptions, Boolean> base, OptionGroup.Builder builder) {
        OptionImpl<RuOKGameOptions, Boolean> option = OptionImpl.createBuilder(Boolean.class, OptionsStorage.getOptionStorage())
                .setName(Component.translatable(base.getKey() + ".info"))
                .setTooltip(Component.translatable(base.getKey() + ".tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(base.getBiConsumer(), base.getFunction())
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        builder.add(option);

    }


    private static void processInteger(Base<RuOKGameOptions, Integer> base, OptionGroup.Builder builder) {
        OptionImpl<RuOKGameOptions, Integer> option = OptionImpl.createBuilder(int.class, OptionsStorage.getOptionStorage())
                .setName(Component.translatable(base.getKey() + ".info"))
                .setTooltip(Component.translatable(base.getKey() + ".tooltip"))
                .setControl(o -> getIntegerControl(base, o))
                .setBinding(base.getBiConsumer(), base.getFunction())
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        builder.add(option);
    }

    private static @NotNull SliderControl getIntegerControl(Base<RuOKGameOptions, Integer> base, OptionImpl<RuOKGameOptions, Integer> optio) {
        return new SliderControl(optio, base.getMin(), base.getMax(), base.getInterVal(), ControlValueFormatter.translateVariable(base.getFormat()));
    }

    //TODO: this ENUM Option
    private static void processEnum(@NotNull Base<RuOKGameOptions, ?> base, OptionGroup.Builder builder) {
        switch (base.getKey()) {
            case "ruok.quality.global" ->
                    addEnumOption((Base<RuOKGameOptions, QualityType>) base, QualityType.class, builder);
            case "ruok.quality.weather" ->
                    addEnumOption((Base<RuOKGameOptions, WeatherType>) base, WeatherType.class, builder);
            case "ruok.quality.particle" ->
                    addEnumOption((Base<RuOKGameOptions, BlockBreakParticleType>) base, BlockBreakParticleType.class, builder);
        }
    }
    private static <T extends Enum<T>> void addEnumOption(Base<RuOKGameOptions, T> base, Class<T> clazz, OptionGroup.Builder builder) {
        OptionImpl<RuOKGameOptions, T> option = OptionImpl.createBuilder(clazz, OptionsStorage.getOptionStorage())
                .setName(Component.translatable(base.getKey() + ".info"))
                .setTooltip(Component.translatable(base.getKey() + ".tooltip"))
                .setControl(o -> new CyclingControl<>(o, clazz, base.getTexts()))
                .setBinding(base.getBiConsumer(), base.getFunction())
                .setImpact(OptionImpact.HIGH)
                .build();
        builder.add(option);
    }
}
