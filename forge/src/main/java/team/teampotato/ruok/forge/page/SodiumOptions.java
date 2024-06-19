package team.teampotato.ruok.forge.page;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.util.QualityUtil;
import team.teampotato.ruok.forge.util.Render;
import team.teampotato.ruok.forge.util.Weather;

import java.util.ArrayList;
import java.util.List;

public class SodiumOptions {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();
    @Contract(" -> new")
    public static @NotNull OptionPage RuOKPages() {
        List<OptionGroup> groups = new ArrayList<>();
        OptionImpl<SodiumGameOptions, Boolean> AutoQuality = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.autoquality.info"))
                .setTooltip(new TranslatableText("ruok.quality.autoquality.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().AutoQuality = value;
                            //Assessment.run();
                            RuOK.save();
                        },
                        (options) ->
                                RuOK.get().AutoQuality)
                .setImpact(OptionImpact.HIGH)
                .build();

        OptionImpl<SodiumGameOptions, QualityUtil.QualityMode> GlobalQuality = OptionImpl.createBuilder(QualityUtil.QualityMode.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.globalquality.info"))
                .setTooltip(new TranslatableText("ruok.quality.globalquality.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, QualityUtil.QualityMode.class, new Text[] {
                        new TranslatableText("ruok.quality.globalquality.set.ultra"),
                        new TranslatableText("ruok.quality.globalquality.set.high"),
                        new TranslatableText("ruok.quality.globalquality.set.normal"),
                        new TranslatableText("ruok.quality.globalquality.set.low"),
                        new TranslatableText("ruok.quality.globalquality.set.critical"),
                        new TranslatableText("ruok.quality.globalquality.set.custom")
                }))
                .setBinding(
                        (opts, value) -> {
                            QualityUtil.set(value,false);
                            RuOK.get().QualityModes = value;
                            RuOK.save();
                        },
                        (opts) -> RuOK.get().QualityModes // 获取当前配置类中的 qualityMode 字段值
                )
                .setImpact(OptionImpact.HIGH)
                .build();
        OptionImpl<SodiumGameOptions, Integer> RenderDistances = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.renderlivingdistance.info"))
                .setTooltip(new TranslatableText("ruok.quality.renderlivingdistance.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 128, 1, ControlValueFormatter.quantity("ruok.quality.options.block")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().entitiesDistance = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().entitiesDistance

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();


        OptionImpl<SodiumGameOptions, Integer> RenderEntityEntity = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.renderentityentity.info"))
                .setTooltip(new TranslatableText("ruok.quality.renderentityentity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 512, 1, ControlValueFormatter.quantity("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().maxEntityEntities = value;
                            RuOK.save();
                            Render.reloadRenderEntity();
                        },
                        (options) -> RuOK.get().maxEntityEntities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();



        groups.add(OptionGroup.createBuilder()
                .add(RenderDistances)//渲染实体/生物距离
                .add(RenderEntityEntity)//实体渲染数量
                .add(GlobalQuality)//全局画质
               // .add(AutoQuality)//画质调整
                .build());
        return new OptionPage(new TranslatableText("ruok.options.pages.ruok.main"), ImmutableList.copyOf(groups));
    }
    @Contract(" -> new")
    public static @NotNull OptionPage RuOKPagesOther() {
        List<OptionGroup> groups = new ArrayList<>();
        OptionImpl<SodiumGameOptions, Boolean> RenderTNTExplosions  = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.rendertntexplosions.info"))
                .setTooltip(new TranslatableText("ruok.quality.rendertntexplosions.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderTNTExplosions= value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().RenderTNTExplosions

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();



        //快速物品
        OptionImpl<SodiumGameOptions, Boolean> RenderFastItem = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.renderfastitem.info"))
                .setTooltip(new TranslatableText("ruok.quality.renderfastitem.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().FastItemRender = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().FastItemRender

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();


        //快速物品
        OptionImpl<SodiumGameOptions, Boolean> RenderDisplayItem = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.renderdisplayitem.info"))
                .setTooltip(new TranslatableText("ruok.quality.renderdisplayitem.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderDisplayItem = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().RenderDisplayItem

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        //天气渲染
        OptionImpl<SodiumGameOptions, Weather> RenderWeather = OptionImpl.createBuilder(Weather.class, sodiumOpts)
                .setName(new TranslatableText("ruok.quality.renderweather.info"))
                .setTooltip(new TranslatableText("ruok.quality.renderweather.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, Weather.class, new Text[] {
                        new TranslatableText("ruok.quality.renderweather.set.normal"),
                        new TranslatableText("ruok.quality.renderweather.set.low"),
                        new TranslatableText("ruok.quality.renderweather.set.close")
                }))
                .setBinding(
                        (opts, value) -> {
                           RuOK.get().RenderWeather = value;
                           RuOK.save();
                        },
                        (opts) -> RuOK.get().RenderWeather // 获取当前配置类中的 qualityMode 字段值
                )
                .setImpact(OptionImpact.LOW)
                .build();



        groups.add(OptionGroup.createBuilder()
                .add(RenderWeather)//天气渲染
                .add(RenderTNTExplosions)//TNT渲染
                .add(RenderFastItem)//物品快速渲染
                .add(RenderDisplayItem)//掉落物展示名称
                .build()
        );
        return new OptionPage(new TranslatableText("ruok.options.pages.ruok.other"), ImmutableList.copyOf(groups));
    }

}
