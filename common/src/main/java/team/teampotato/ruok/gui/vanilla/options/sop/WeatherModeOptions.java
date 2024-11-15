package team.teampotato.ruok.gui.vanilla.options.sop;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.WeatherMode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static team.teampotato.ruok.gui.vanilla.mode.WeatherMode.values;

public class WeatherModeOptions {
    public static OptionInstance<WeatherMode> getWeatherModeOptionInstance() {
        return new OptionInstance<>("ruok.quality.weather.info", (value) -> {
            switch (value) {
                case CLOSE -> {
                    return Tooltip.create(Component.translatable("ruok.quality.close"));
                }
                case LOW -> {
                    return Tooltip.create(Component.translatable("ruok.quality.low"));
                }
                case NORMAL -> {
                    return Tooltip.create(Component.translatable("ruok.quality.normal"));
                }
                default -> throw new IncompatibleClassChangeError();
            }
        }, (optionText, value) -> Component.translatable(value.getKey()),
                new OptionInstance.AltEnum<>(Arrays.asList(values()), Stream.of(values()).collect(Collectors.toList()), () -> true, (option, mode) -> {
                    Minecraft mci = Minecraft.getInstance();
                    option.set(mode);
                    RuOK.get().RenderWeather = mode;
                    RuOK.save();
                    mci.levelRenderer.allChanged();
                }, Codec.INT.xmap(WeatherMode::byId, WeatherMode::getId)), RuOK.get().RenderWeather, (value) -> {

        });
    }
}
