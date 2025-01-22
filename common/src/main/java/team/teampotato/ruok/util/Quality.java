package team.teampotato.ruok.util;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.ParticleStatus;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.BlockBreakParticleType;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;

import java.util.EnumMap;

public class Quality {

    private static final EnumMap<QualityType, QualitySettings> QUALITY_SETTINGS_MAP = new EnumMap<>(QualityType.class);

    static {
        QUALITY_SETTINGS_MAP.put(QualityType.ULTRA, new QualitySettings(WeatherType.NORMAL, true, 16, false, ParticleStatus.ALL, GraphicsStatus.FABULOUS, true, CloudStatus.FANCY, true, 1024, 512,BlockBreakParticleType.VERY_HIGH,512,true));
        QUALITY_SETTINGS_MAP.put(QualityType.HIGH, new QualitySettings(WeatherType.NORMAL, true, 12, true, ParticleStatus.MINIMAL, GraphicsStatus.FANCY, true, CloudStatus.FANCY, true, 512, 256,BlockBreakParticleType.HIGH,256,true));
        QUALITY_SETTINGS_MAP.put(QualityType.NORMAL, new QualitySettings(WeatherType.NORMAL, true, 8, true, ParticleStatus.ALL, GraphicsStatus.FABULOUS, true, CloudStatus.FAST, true, 256,  128,BlockBreakParticleType.NORMAL,128,true));
        QUALITY_SETTINGS_MAP.put(QualityType.LOW, new QualitySettings(WeatherType.LOW, true, 6, true, ParticleStatus.MINIMAL, GraphicsStatus.FAST, true, CloudStatus.OFF, false, 128,  64,BlockBreakParticleType.LOW,64,true));
        QUALITY_SETTINGS_MAP.put(QualityType.CRITICAL, new QualitySettings(WeatherType.CLOSE, false, 2, true, ParticleStatus.MINIMAL, GraphicsStatus.FAST, false, CloudStatus.OFF, false, 64, 32,BlockBreakParticleType.LOW,32,false));
    }

    public static void set(@NotNull QualityType mode) {
        QualitySettings settings = QUALITY_SETTINGS_MAP.get(mode);
        applySettings(settings);
        RuOK.get().qualityModes = mode;

    }

    private static void applySettings(@NotNull QualitySettings settings) {
        RuOptions.setGraphicsMode(settings.GraphicsStatus);
        RuOptions.setViewDistance(settings.viewDistance);
        RuOptions.setVsync(settings.vsync);
        RuOptions.setParticles(settings.ParticleStatus);
        RuOptions.setAo(settings.ao);
        RuOptions.setCloudRenderMode(settings.CloudStatus);
        RuOptions.setEntityShadows(settings.entityShadows);
        RuOK.get().MaxEntityEntities = settings.maxEntityEntities;
        RuOK.get().EntitiesDistance = settings.entitiesDistance;
        RuOK.get().RenderWeather = settings.weather;
        RuOK.get().BlockBreakParticleMode = settings.blockBreakParticleType;
        RuOK.get().MaxParticleDistance = settings.maxParticleDistance;
        RuOK.get().Particle = settings.particle;

    }
    private record QualitySettings(WeatherType weather, boolean renderTNTExplosions, int viewDistance, boolean vsync,
                                   ParticleStatus ParticleStatus, GraphicsStatus GraphicsStatus, boolean ao,
                                   CloudStatus CloudStatus, boolean entityShadows,
                                   int maxEntityEntities, int entitiesDistance, BlockBreakParticleType blockBreakParticleType,
                                   int maxParticleDistance, boolean particle
    ) {}
}
