package team.teampotato.ruok.fabric.client.gui.options;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.text.Text;
import team.teampotato.ruok.fabric.client.Assessment.RunBenchmark;
import team.teampotato.ruok.fabric.client.QualityMode;
import team.teampotato.ruok.fabric.config.RuOK;

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
        OptionImpl<SodiumGameOptions, Integer> RenderLivingDistances = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderlivingdistance.info"))
                .setTooltip(Text.translatable("ruok.quality.renderlivingdistance.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 1000, 1, ControlValueFormatter.translateVariable("ruok.quality.options.block")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Render_Entities_Distance = value;
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
                        },
                        (options) -> RuOK.get().Max_Rendered_LivingEntities

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

        OptionImpl<SodiumGameOptions, Integer> RenderEntityEntity = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderentityentity.info"))
                .setTooltip(Text.translatable("ruok.quality.renderentityentity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 500, 1, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Max_Rendered_EntityEntities = value;
                        },
                        (options) -> RuOK.get().Max_Rendered_EntityEntities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        OptionImpl<SodiumGameOptions, Integer> RenderMaxParticleCount = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.rendermaxpartclecount.info"))
                .setTooltip(Text.translatable("ruok.quality.rendermaxpartclecount.tooltip"))
                .setControl(option -> new SliderControl(option, 1, 16384, 1, ControlValueFormatter.translateVariable("ruok.quality.options.particle")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().MaxParticleCount = value;
                        },
                        (options) -> RuOK.get().MaxParticleCount

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
/*


        OptionImpl<SodiumGameOptions, Integer> RenderMaxDropItemStack = OptionImpl.createBuilder(int.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.rendermaxdropitemstack.info"))
                .setTooltip(Text.translatable("ruok.quality.rendermaxdropitemstack.tooltip"))
                .setControl(option -> new SliderControl(option, 1, 1000, 1, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().MaxDropItemStack = value;
                        },
                        (options) -> RuOK.get().MaxDropItemStack

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

 */

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
        //雨雪网络包更改
        //雨
        OptionImpl<SodiumGameOptions, Boolean> RenderWeatherNetwork = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderweathernetwork.info"))
                .setTooltip(Text.translatable("ruok.quality.renderweathernetwork.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().WeatherNetwork = value;
                        },
                        (options) -> RuOK.get().WeatherNetwork

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        //雨
        OptionImpl<SodiumGameOptions, Boolean> RenderRain = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderrain.info"))
                .setTooltip(Text.translatable("ruok.quality.renderrain.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().RainRender = value;
                        },
                        (options) -> RuOK.get().RainRender

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();

        OptionImpl<SodiumGameOptions, Boolean> RenderThunder = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderthunder.info"))
                .setTooltip(Text.translatable("ruok.quality.renderthunder.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().ThunderRender = value;
                        },
                        (options) -> RuOK.get().ThunderRender

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        //雨
        OptionImpl<SodiumGameOptions, Boolean> RenderLighting = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderlighting.info"))
                .setTooltip(Text.translatable("ruok.quality.renderlighting.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().LightingUpdate= value;
                        },
                        (options) -> RuOK.get().LightingUpdate

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
                        },
                        (options) -> RuOK.get().FastItemRender

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();
        //物品阴影
        OptionImpl<SodiumGameOptions, Boolean> RenderItemCastShadows = OptionImpl.createBuilder(Boolean.class, sodiumOpts)
                .setName(Text.translatable("ruok.quality.renderitemcastshadows.info"))
                .setTooltip(Text.translatable("ruok.quality.renderitemcastshadows.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().ItemCastShadows= value;
                        },
                        (options) -> RuOK.get().LightingUpdate

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();






        groups.add(OptionGroup.createBuilder()
                .add(RenderLivingDistances)
                .add(RenderLivingEntity)
                .add(RenderEntityEntity)
              //  .add(RenderMaxDropItemStack)
                .add(RenderMaxParticleCount)
                .add(GlobalQuality)
                .add(AutoQuality)//画质调整
                .add(RenderLeaves)//树叶渲染
                .add(RenderMangroveRoots)//红树根
                .add(RenderWeatherNetwork)//网络包
                .add(RenderRain)//雨
                .add(RenderThunder)//雪
                .add(RenderLighting)//光照tick
                .add(RenderFastItem)//物品快速渲染
                .add(RenderItemCastShadows)//物品阴影
                .build());
        return new OptionPage(Text.translatable("ruok.options.pages.ruok.main"), ImmutableList.copyOf(groups));
    }

}
