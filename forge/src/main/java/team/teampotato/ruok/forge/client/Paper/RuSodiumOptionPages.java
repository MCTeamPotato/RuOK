package team.teampotato.ruok.forge.client.Paper;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.text.Text;
import team.teampotato.ruok.forge.client.Assessment.RunBenchmark;
import team.teampotato.ruok.forge.client.QualityMode;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.ArrayList;
import java.util.List;

public class RuSodiumOptionPages {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();

    public static OptionPage RuOKPages() {
        List<OptionGroup> groups = new ArrayList<>();
        OptionImpl<SodiumGameOptions, Boolean> AutoQuality = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.autoquality.info"))
                .setTooltip(Text.translatable("ruok.quality.autoquality.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.setAutoQuality(value);
                            RunBenchmark.Run();
                        },
                        (options) ->
                                RuOK.get().AutoQuality)
                .setImpact(OptionImpact.HIGH)
                .build();

        OptionImpl<SodiumGameOptions, QualityMode> GlobalQuality = OptionImpl.createBuilder(QualityMode.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.globalquality.info"))
                .setTooltip(Text.translatable("ruok.quality.globalquality.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, QualityMode.class, new Text[] {
                        Text.translatable("ruok.quality.globalquality.set.ultra"),
                        Text.translatable("ruok.quality.globalquality.set.high"),
                        Text.translatable("ruok.quality.globalquality.set.normal"),
                        Text.translatable("ruok.quality.globalquality.set.low"),
                        Text.translatable("ruok.quality.globalquality.set.critical"),
                        Text.translatable("ruok.quality.globalquality.set.custom")
                }))
                .setBinding(
                        (opts, value) -> {
                            RuOK.setQualityMode(value);
                        },
                        (opts) -> RuOK.get().QualityModes // 获取当前配置类中的 qualityMode 字段值
                )
                .setImpact(OptionImpact.HIGH)
                .build();
        OptionImpl<SodiumGameOptions, Integer> RenderDistances = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderdistance.info"))
                .setTooltip(Text.translatable("ruok.quality.renderdistance.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 1000, 1, ControlValueFormatter.translateVariable("ruok.quality.options.block")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderDistance = value;
                        },
                        (options) -> RuOK.get().RenderDistance

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        OptionImpl<SodiumGameOptions, Integer> RenderEntity = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderentity.info"))
                .setTooltip(Text.translatable("ruok.quality.renderentity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 500, 1, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Max_Rendered_Entities = value;
                        },
                        (options) -> RuOK.get().Max_Rendered_Entities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        OptionImpl<SodiumGameOptions, Boolean> RenderLeaves = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderleaves.info"))
                .setTooltip(Text.translatable("ruok.quality.renderleaves.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderLeaves = value;
                        },
                        (options) -> RuOK.get().RenderLeaves

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        OptionImpl<SodiumGameOptions, Boolean> RenderMangroveRoots = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.rendermangroveroots.info"))
                .setTooltip(Text.translatable("ruok.quality.rendermangroveroots.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderMangroveRoots = value;
                        },
                        (options) -> RuOK.get().RenderMangroveRoots

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();


        groups.add(OptionGroup.createBuilder()
                .add(RenderDistances)
                .add(RenderEntity)
                .add(GlobalQuality)
                .add(AutoQuality)
                .add(RenderLeaves)
                .add(RenderMangroveRoots)
                .build());
        return new OptionPage(Text.translatable("ruok.options.pages.ruok"), ImmutableList.copyOf(groups));
    }

}
