package team.teampotato.ruok.util;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.ParticleStatus;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.QualityMode;
import team.teampotato.ruok.gui.vanilla.mode.WeatherMode;

import java.util.EnumMap;

public class Quality {

    private static final EnumMap<QualityMode, QualitySettings> QUALITY_SETTINGS_MAP = new EnumMap<>(QualityMode.class);

    static {
        QUALITY_SETTINGS_MAP.put(QualityMode.ULTRA, new QualitySettings(WeatherMode.NORMAL, true, 16, false, ParticleStatus.ALL, GraphicsStatus.FABULOUS, true, CloudStatus.FANCY, true, 1024, 512));
        QUALITY_SETTINGS_MAP.put(QualityMode.HIGH, new QualitySettings(WeatherMode.NORMAL, true, 12, true, ParticleStatus.MINIMAL, GraphicsStatus.FANCY, true, CloudStatus.FANCY, true, 512, 256));
        QUALITY_SETTINGS_MAP.put(QualityMode.NORMAL, new QualitySettings(WeatherMode.NORMAL, true, 8, true, ParticleStatus.ALL, GraphicsStatus.FABULOUS, true, CloudStatus.FAST, true, 256,  128));
        QUALITY_SETTINGS_MAP.put(QualityMode.LOW, new QualitySettings(WeatherMode.LOW, true, 6, true, ParticleStatus.MINIMAL, GraphicsStatus.FAST, true, CloudStatus.OFF, false, 128,  64));
        QUALITY_SETTINGS_MAP.put(QualityMode.CRITICAL, new QualitySettings(WeatherMode.CLOSE, false, 2, true, ParticleStatus.MINIMAL, GraphicsStatus.FAST, false, CloudStatus.OFF, false, 64, 32));
    }

    public static void set(@NotNull QualityMode mode) {
        QualitySettings settings = QUALITY_SETTINGS_MAP.get(mode);
        applySettings(settings);
        RuOK.get().QualityModes = mode;
    }

    private static void applySettings(@NotNull QualitySettings settings) {
        RuOptions.setViewDistance(settings.viewDistance);
        RuOptions.setVsync(settings.vsync);
        RuOptions.setParticles(settings.ParticleStatus);
        RuOptions.setGraphicsMode(settings.GraphicsStatus);
        RuOptions.setAo(settings.ao);
        RuOptions.setCloudRenderMode(settings.CloudStatus);
        RuOptions.setEntityShadows(settings.entityShadows);
        RuOK.get().maxEntityEntities = settings.maxEntityEntities;
        RuOK.get().entitiesDistance = settings.entitiesDistance;
        RuOK.get().RenderWeather = settings.weather;
        RuOK.get().RenderTNTExplosions = settings.renderTNTExplosions;
    }
    private record QualitySettings(WeatherMode weather, boolean renderTNTExplosions, int viewDistance, boolean vsync,
                                   ParticleStatus ParticleStatus, GraphicsStatus GraphicsStatus, boolean ao,
                                   CloudStatus CloudStatus, boolean entityShadows,
                                   int maxEntityEntities, int entitiesDistance) {}
}
