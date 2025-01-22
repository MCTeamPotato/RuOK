package team.teampotato.ruok.gui.vanilla.options;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.BlockBreakParticleType;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;
import team.teampotato.ruok.util.Quality;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static team.teampotato.ruok.gui.vanilla.mode.WeatherType.values;

public class EnumOptions {
    @Contract(" -> new")
    public static @NotNull OptionInstance<WeatherType> getWeatherModeOptionInstance() {
        return new OptionInstance<>(
                "ruok.quality.weather.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.weather.tooltip")),
                (optionText, value) -> Component.translatable(value.getKey()),
                new OptionInstance.AltEnum<>(
                        Arrays.asList(values()),
                        Stream.of(values()).collect(Collectors.toList()),
                        () -> true,
                        (option, mode) -> {
                            option.set(mode);
                            RuOK.get().RenderWeather = mode;
                            RuOK.save();
                        },
                        Codec.INT.xmap(WeatherType::byId, WeatherType::getId)
                ),
                RuOK.get().RenderWeather,
                (value) -> {

                }
        );
    }
    @Contract(" -> new")
    public static @NotNull OptionInstance<QualityType> getQualityModeOptionInstance() {
        return new OptionInstance<>(
                "ruok.quality.global.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.global.tooltip")),
                (optionText, value) -> Component.translatable(value.getKey()),
                new OptionInstance.AltEnum<>(
                        Arrays.asList(QualityType.values()),
                        Stream.of(QualityType.values()).collect(Collectors.toList()),
                        () -> true,
                        (option, mode) -> {
                            Quality.set(mode);
                            RuOK.get().qualityModes = mode;
                            RuOK.save();
                        },
                        Codec.INT.xmap(QualityType::byId, QualityType::getId)
                ),
                RuOK.get().qualityModes,
                (value) -> {

                }
        );
    }
    @Contract(" -> new")
    public static @NotNull OptionInstance<BlockBreakParticleType> getBlockBreakParticleTypeOption() {
        return new OptionInstance<>(
                "ruok.quality.particle.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.particle.tooltip")),
                (optionText, value) -> Component.translatable(value.getKey()),
                new OptionInstance.AltEnum<>(
                        Arrays.asList(BlockBreakParticleType.values()),
                        Stream.of(BlockBreakParticleType.values()).collect(Collectors.toList()),
                        () -> true,
                        (option, mode) -> {
                            RuOK.get().BlockBreakParticleMode = mode;
                            RuOK.save();
                            },
                        Codec.INT.xmap(BlockBreakParticleType::byId, BlockBreakParticleType::getId)
                ),
                RuOK.get().BlockBreakParticleMode,
                (value) -> {

                }
        );
    }
}
