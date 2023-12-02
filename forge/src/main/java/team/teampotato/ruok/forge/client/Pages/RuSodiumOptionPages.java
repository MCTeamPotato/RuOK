package team.teampotato.ruok.forge.client.Pages;

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
                            RuOK.save();
                        },
                        (options) ->
                                RuOK.get().AutoQuality)
                .setImpact(OptionImpact.HIGH)
                .build();

        OptionImpl<SodiumGameOptions, RuOK.qualityMode> GlobalQuality = OptionImpl.createBuilder(RuOK.qualityMode.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.globalquality.info"))
                .setTooltip(Text.translatable("ruok.quality.globalquality.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, RuOK.qualityMode.class, new Text[] {
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
                            RuOK.save();
                        },
                        (opts) -> RuOK.get().QualityModes // 获取当前配置类中的 qualityMode 字段值
                )
                .setImpact(OptionImpact.HIGH)
                .build();
        OptionImpl<SodiumGameOptions, Integer> RenderLivingDistances = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderlivingdistance.info"))
                .setTooltip(Text.translatable("ruok.quality.renderlivingdistance.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 1000, 1, ControlValueFormatter.translateVariable("ruok.quality.options.block")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Render_Entities_Distance = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().Render_Entities_Distance

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        OptionImpl<SodiumGameOptions, Integer> RenderLivingEntity = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderlivingentity.info"))
                .setTooltip(Text.translatable("ruok.quality.renderlivingentity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 500, 1, ControlValueFormatter.translateVariable("ruok.quality.options.lightingentity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Max_Rendered_LivingEntities = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().Max_Rendered_LivingEntities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();





        OptionImpl<SodiumGameOptions, Integer> RenderEntityEntity = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderentityentity.info"))
                .setTooltip(Text.translatable("ruok.quality.renderentityentity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 500, 1, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Max_Rendered_EntityEntities = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().Max_Rendered_EntityEntities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();



        groups.add(OptionGroup.createBuilder()
                .add(RenderLivingDistances)//渲染实体/生物距离
                .add(RenderLivingEntity)//渲染生物
                .add(RenderEntityEntity)//渲染实体
                .add(GlobalQuality)//全局画质
                .add(AutoQuality)//画质调整
                .build());
        return new OptionPage(Text.translatable("ruok.options.pages.ruok.main"), ImmutableList.copyOf(groups));
    }
    public static OptionPage RuOKPagesOther() {
        List<OptionGroup> groups = new ArrayList<>();


        OptionImpl<SodiumGameOptions, Boolean> RenderLighting = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderlighting.info"))
                .setTooltip(Text.translatable("ruok.quality.renderlighting.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RenderLighting = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().RenderLighting

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();



        OptionImpl<SodiumGameOptions, Boolean> RenderTNTExplosions  = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.rendertntexplosions.info"))
                .setTooltip(Text.translatable("ruok.quality.rendertntexplosions.tooltip"))
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
                .setName(Text.translatable("ruok.quality.renderfastitem.info"))
                .setTooltip(Text.translatable("ruok.quality.renderfastitem.tooltip"))
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
                .setName(Text.translatable("ruok.quality.renderdisplayitem.info"))
                .setTooltip(Text.translatable("ruok.quality.renderdisplayitem.tooltip"))
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
        OptionImpl<SodiumGameOptions, RuOK.weather> RenderWeather = OptionImpl.createBuilder(RuOK.weather.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderweather.info"))
                .setTooltip(Text.translatable("ruok.quality.renderweather.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, RuOK.weather.class, new Text[] {
                        Text.translatable("ruok.quality.renderweather.set.normal"),
                        Text.translatable("ruok.quality.renderweather.set.low"),
                        Text.translatable("ruok.quality.renderweather.set.close")
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
                .add(RenderLighting)//光照
                .add(RenderWeather)//天气渲染
                .add(RenderTNTExplosions)//TNT渲染
                .add(RenderFastItem)//物品快速渲染
                .add(RenderDisplayItem)//掉落物展示名称
                .build()
        );
        return new OptionPage(Text.translatable("ruok.options.pages.ruok.other"), ImmutableList.copyOf(groups));
    }

}
