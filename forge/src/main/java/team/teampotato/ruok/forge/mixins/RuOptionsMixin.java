package team.teampotato.ruok.forge.mixins;


import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.teampotato.ruok.forge.client.Assessment.RunBenchmark;
import team.teampotato.ruok.forge.client.QualityMode;
import team.teampotato.ruok.forge.client.RuOK;


import java.util.List;


@Mixin(SodiumGameOptionPages.class)
public class RuOptionsMixin
{
    @Shadow @Final private static SodiumOptionsStorage sodiumOpts;
    @Inject(
            method = "general",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;copyOf(Ljava/util/Collection;)Lcom/google/common/collect/ImmutableList;"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false,
            cancellable = true
    )
    private static void onInject(CallbackInfoReturnable<OptionPage> cir, List<OptionGroup> groups)
    {
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
            .setControl(option -> new SliderControl(option, 2, 300, 1, ControlValueFormatter.translateVariable("ruok.quality.options.block")))
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
                .setName(Text.translatable("ruok.quality.renderEntity.info"))
                .setTooltip(Text.translatable("ruok.quality.renderEntity.tooltip"))
                .setControl(option -> new SliderControl(option, 2, 300, 1, ControlValueFormatter.translateVariable("ruok.quality.options.entity")))
                .setBinding(
                        (options, value) -> {
                            RuOK.get().Max_Rendered_Entities = value;
                        },
                        (options) -> RuOK.get().Max_Rendered_Entities

                )
                .setImpact(OptionImpact.LOW)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build();


        groups.add(OptionGroup.createBuilder()
               .add(RenderDistances)
               .add(RenderEntity)
               .add(GlobalQuality)
               .add(AutoQuality)
               .build());

    }

}