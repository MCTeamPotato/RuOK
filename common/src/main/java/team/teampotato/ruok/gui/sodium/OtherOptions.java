package team.teampotato.ruok.gui.sodium;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.WeatherMode;

import java.util.ArrayList;
import java.util.List;

public class OtherOptions {
    public static OptionPage RuOKPagesOther() {
        List<OptionGroup> groups = new ArrayList<>();
        OptionImpl<SodiumGameOptions, Boolean> TNTExplosions  = OptionImpl.createBuilder(Boolean.class, OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.tntexplosions.info"))
                .setTooltip(Component.translatable("ruok.quality.tntexplosions.tooltip"))
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
        OptionImpl<SodiumGameOptions, Boolean> fastItem = OptionImpl.createBuilder(Boolean.class, OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.fastitem.info"))
                .setTooltip(Component.translatable("ruok.quality.fastitem.tooltip"))
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
        OptionImpl<SodiumGameOptions, Boolean> displayItem = OptionImpl.createBuilder(Boolean.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.displayitem.info"))
                .setTooltip(Component.translatable("ruok.quality.displayitem.tooltip"))
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
        OptionImpl<SodiumGameOptions, WeatherMode> renderWeather = OptionImpl.createBuilder(WeatherMode.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.weather.info"))
                .setTooltip(Component.translatable("ruok.quality.weather.tooltip"))
                .setControl((option) -> new CyclingControl<>(option, WeatherMode.class, new Component[] {
                        Component.translatable("ruok.quality.close"),
                        Component.translatable("ruok.quality.low"),
                        Component.translatable("ruok.quality.normal")
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

        OptionImpl<SodiumGameOptions, Boolean> renderItemCount = OptionImpl.createBuilder(Boolean.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.itemcount.info"))
                .setTooltip(Component.translatable("ruok.quality.itemcount.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().isAlwaysShowItemCount = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().isAlwaysShowItemCount)
                .setImpact(OptionImpact.LOW)
                .build();

        OptionImpl<SodiumGameOptions, Boolean> chatFix = OptionImpl.createBuilder(Boolean.class,OptionsStorage.getSodiumOpts())
                .setName(Component.translatable("ruok.quality.chatfix.info"))
                .setTooltip(Component.translatable("ruok.quality.chatfix.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (options, value) -> {
                            RuOK.get().chatFix = value;
                            RuOK.save();
                        },
                        (options) -> RuOK.get().chatFix)
                .setImpact(OptionImpact.LOW)
                .build();




        groups.add(OptionGroup.createBuilder()
                .add(renderWeather)//天气渲染
                .add(TNTExplosions)//TNT渲染
                .add(fastItem)//物品快速渲染
                .add(displayItem)//掉落物展示名称
                .add(renderItemCount)//掉落物数量渲染
                .add(chatFix)//聊天框修复
                .build()
        );
        return new OptionPage(Component.translatable("ruok.options.pages.ruok.other"), ImmutableList.copyOf(groups));
    }

}
