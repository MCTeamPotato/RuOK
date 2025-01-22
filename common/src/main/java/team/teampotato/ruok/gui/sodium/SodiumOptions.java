package team.teampotato.ruok.gui.sodium;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.util.Quality;
import team.teampotato.ruok.util.Render;
import team.teampotato.ruok.vellamo.Score;

import java.util.ArrayList;
import java.util.List;

public class SodiumOptions {


    @Contract(" -> new")
    public static @NotNull OptionPage RuOKPages() {
        List<OptionGroup> groups = new ArrayList<>();
        //剔除
        OptionImpl<SodiumGameOptions, Boolean> onCull = OptionImpl.createBuilder(Boolean.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.cull.info"))
                .setTooltip(Component.translatable("ruok.quality.cull.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().onCull = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().onCull

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        OptionImpl<SodiumGameOptions, QualityType> globalQuality = OptionImpl.createBuilder(QualityType.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.global.info"))
                .setTooltip(Component.translatable("ruok.quality.global.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, QualityType.class, new Component[] {
                        Component.translatable("ruok.quality.critical"),
                        Component.translatable("ruok.quality.low"),
                        Component.translatable("ruok.quality.normal"),
                        Component.translatable("ruok.quality.high"),
                        Component.translatable("ruok.quality.ultra"),
                }))
                .setBinding(
                        (opts, value) -> {
                            Quality.set(value);
                            RuOK.get().qualityModes = value;
                            RuOK.save();
                        },
                        (opts) -> RuOK.get().qualityModes // 获取当前配置类中的 qualityMode 字段值
                )
                .setImpact(OptionImpact.HIGH)
                .build();
        OptionImpl<SodiumGameOptions, Integer> entityDistances = OptionImpl.createBuilder(int.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.distance.info"))
                .setTooltip(Component.translatable("ruok.quality.distance.tooltip"))
                .setControl(option -> new SliderControl(option, 4, 512, 1, ControlValueFormatter.translateVariable("ruok.quality.options.block")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().EntitiesDistance = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().EntitiesDistance

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        OptionImpl<SodiumGameOptions, Integer> entityCount = OptionImpl.createBuilder(int.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.entity.info"))
                .setTooltip(Component.translatable("ruok.quality.entity.tooltip"))
                .setControl(option -> new SliderControl(option, 8, 1024, 8, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().MaxEntityEntities = value;
                            RuOK.save();
                            Render.reloadRenderEntity();
                        },
                        (options) -> RuOK.get().MaxEntityEntities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        //画质推荐
        OptionImpl<SodiumGameOptions, Boolean> runScore = OptionImpl.createBuilder(Boolean.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.runscore.info"))
                .setTooltip(Component.translatable("ruok.quality.runscore.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {

                            if(value) Score.runVellamo();
                        },
                        (options) -> false

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();


        groups.add(OptionGroup.createBuilder()
                .add(onCull)//开启剔除
                .add(entityDistances)//渲染实体/生物距离
                .add(entityCount)//实体渲染数量
                .add(globalQuality)//全局画质
                .add(runScore)

                .build());
        return new OptionPage(Component.translatable("ruok.options.pages.ruok.main"), ImmutableList.copyOf(groups));
    }

}
