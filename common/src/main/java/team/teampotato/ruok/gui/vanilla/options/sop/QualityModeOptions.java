package team.teampotato.ruok.gui.vanilla.options.sop;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.QualityMode;
import team.teampotato.ruok.util.Quality;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static team.teampotato.ruok.gui.vanilla.mode.QualityMode.values;

public class QualityModeOptions {
    public static OptionInstance<QualityMode> getQualityModeSimpleOption() {
        return new OptionInstance<>("ruok.quality.global.info", (value) -> {
            switch (value) {
                case CRITICAL -> {
                    return Tooltip.create(Component.translatable("ruok.quality.close"));
                }
                case LOW -> {
                    return Tooltip.create(Component.translatable("ruok.quality.low"));
                }
                case NORMAL -> {
                    return Tooltip.create(Component.translatable("ruok.quality.normal"));
                }
                case HIGH -> {
                    return Tooltip.create(Component.translatable("ruok.quality.high"));
                }
                case ULTRA -> {
                    return Tooltip.create(Component.translatable("ruok.quality.ultra"));
                }

                default -> throw new IncompatibleClassChangeError();
            }
        }, (optionText, value) -> Component.translatable(value.getKey()),
                new OptionInstance.AltEnum<>(Arrays.asList(values()), Stream.of(values()).collect(Collectors.toList()), () -> true, (option, mode) -> {
                    Quality.set(mode);
                    RuOK.get().QualityModes = mode;
                    RuOK.save();
                }, Codec.INT.xmap(QualityMode::byId, QualityMode::getId)), RuOK.get().QualityModes, (value) -> {

        });
    }
}
