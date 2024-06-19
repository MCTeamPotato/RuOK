package team.teampotato.ruok.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;

import java.util.EnumMap;

public class QualityUtil {

    private static final EnumMap<QualityMode, QualitySettings> QUALITY_SETTINGS_MAP = new EnumMap<>(QualityMode.class);

    static {
        QUALITY_SETTINGS_MAP.put(QualityMode.ULTRA, new QualitySettings(Weather.NORMAL, true, 20, false, ParticlesMode.ALL, GraphicsMode.FABULOUS, AoMode.MAX, CloudRenderMode.FANCY, true, 1000, 1000, 500));
        QUALITY_SETTINGS_MAP.put(QualityMode.HIGH, new QualitySettings(Weather.NORMAL, true, 16, true, ParticlesMode.MINIMAL, GraphicsMode.FANCY, AoMode.MAX, CloudRenderMode.FANCY, true, 512, 1000, 256));
        QUALITY_SETTINGS_MAP.put(QualityMode.NORMAL, new QualitySettings(Weather.NORMAL, true, 12, true, ParticlesMode.ALL, GraphicsMode.FABULOUS, AoMode.MIN, CloudRenderMode.FAST, true, 256, 512, 128));
        QUALITY_SETTINGS_MAP.put(QualityMode.LOW, new QualitySettings(Weather.LOW, true, 4, true, ParticlesMode.MINIMAL, GraphicsMode.FAST, AoMode.MIN, CloudRenderMode.OFF, false, 128, 256, 96));
        QUALITY_SETTINGS_MAP.put(QualityMode.CRITICAL, new QualitySettings(Weather.CLOSE, false, 2, true, ParticlesMode.MINIMAL, GraphicsMode.FAST, AoMode.OFF, CloudRenderMode.OFF, false, 64, 128, 64));
    }

    public static void set(@NotNull QualityMode mode, boolean isAssessment) {
        QualitySettings settings = QUALITY_SETTINGS_MAP.get(mode);
        if (settings != null) {
            applySettings(settings);
            if(isAssessment)sendMessage(mode);
        }
        RuOK.get().QualityModes = mode;
    }

    private static void applySettings(QualitySettings settings) {
        RuOK.get().RenderWeather = settings.weather;
        RuOK.get().RenderTNTExplosions = settings.renderTNTExplosions;
        Options.setViewDistance(settings.viewDistance);
        Options.setVsync(settings.vsync);
        Options.setParticles(settings.particlesMode);
        Options.setGraphicsMode(settings.graphicsMode);
        Options.setAo(settings.ao);
        Options.setCloudRenderMode(settings.cloudRenderMode);
        Options.setEntityShadows(settings.entityShadows);
        RuOK.get().maxEntityEntities = settings.maxEntityEntities;
        RuOK.get().entitiesDistance = settings.entitiesDistance;
    }

    public static void sendMessage(QualityMode mode) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        mc.player.sendMessage(
                Text.of(I18n.translate("ruok.benchmark.value") + mode));
    }

    public enum QualityMode {
        ULTRA,HIGH,NORMAL,LOW,CRITICAL,CUSTOM
    }

    private record QualitySettings(Weather weather, boolean renderTNTExplosions, int viewDistance, boolean vsync,
                                   ParticlesMode particlesMode, GraphicsMode graphicsMode, AoMode ao,
                                   CloudRenderMode cloudRenderMode, boolean entityShadows, int maxLivingEntities,
                                   int maxEntityEntities, int entitiesDistance) {
    }
}
