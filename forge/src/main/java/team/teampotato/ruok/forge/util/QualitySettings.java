package team.teampotato.ruok.forge.util;

import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;

public class QualitySettings {
    private final Weather weather;
    private final boolean renderTNTExplosions;
    private final int viewDistance;
    private final boolean vsync;
    private final ParticlesMode particlesMode;
    private final GraphicsMode graphicsMode;
    private final AoMode ao;
    private final CloudRenderMode cloudRenderMode;
    private final boolean entityShadows;
    private final int maxLivingEntities;
    private final int maxEntityEntities;
    private final int entitiesDistance;

    public QualitySettings(Weather weather, boolean renderTNTExplosions, int viewDistance, boolean vsync,
                           ParticlesMode particlesMode, GraphicsMode graphicsMode, AoMode ao,
                           CloudRenderMode cloudRenderMode, boolean entityShadows, int maxLivingEntities,
                           int maxEntityEntities, int entitiesDistance) {
        this.weather = weather;
        this.renderTNTExplosions = renderTNTExplosions;
        this.viewDistance = viewDistance;
        this.vsync = vsync;
        this.particlesMode = particlesMode;
        this.graphicsMode = graphicsMode;
        this.ao = ao;
        this.cloudRenderMode = cloudRenderMode;
        this.entityShadows = entityShadows;
        this.maxLivingEntities = maxLivingEntities;
        this.maxEntityEntities = maxEntityEntities;
        this.entitiesDistance = entitiesDistance;
    }

    public Weather getWeather() {
        return weather;
    }

    public boolean isRenderTNTExplosions() {
        return renderTNTExplosions;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public boolean isVsync() {
        return vsync;
    }

    public ParticlesMode getParticlesMode() {
        return particlesMode;
    }

    public GraphicsMode getGraphicsMode() {
        return graphicsMode;
    }

    public AoMode isAo() {
        return ao;
    }

    public CloudRenderMode getCloudRenderMode() {
        return cloudRenderMode;
    }

    public boolean isEntityShadows() {
        return entityShadows;
    }


    public int getMaxEntityEntities() {
        return maxEntityEntities;
    }

    public int getEntitiesDistance() {
        return entitiesDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QualitySettings that = (QualitySettings) o;

        if (renderTNTExplosions != that.renderTNTExplosions) return false;
        if (viewDistance != that.viewDistance) return false;
        if (vsync != that.vsync) return false;
        if (ao != that.ao) return false;
        if (entityShadows != that.entityShadows) return false;
        if (maxLivingEntities != that.maxLivingEntities) return false;
        if (maxEntityEntities != that.maxEntityEntities) return false;
        if (entitiesDistance != that.entitiesDistance) return false;
        if (weather != that.weather) return false;
        if (particlesMode != that.particlesMode) return false;
        if (graphicsMode != that.graphicsMode) return false;
        return cloudRenderMode == that.cloudRenderMode;
    }

    @Override
    public int hashCode() {
        int result = weather != null ? weather.hashCode() : 0;
        result = 31 * result + (renderTNTExplosions ? 1 : 0);
        result = 31 * result + viewDistance;
        result = 31 * result + (vsync ? 1 : 0);
        result = 31 * result + (particlesMode != null ? particlesMode.hashCode() : 0);
        result = 31 * result + (graphicsMode != null ? graphicsMode.hashCode() : 0);
        result = 31 * result + (ao.hashCode());
        result = 31 * result + (cloudRenderMode != null ? cloudRenderMode.hashCode() : 0);
        result = 31 * result + (entityShadows ? 1 : 0);
        result = 31 * result + maxLivingEntities;
        result = 31 * result + maxEntityEntities;
        result = 31 * result + entitiesDistance;
        return result;
    }

    @Override
    public String toString() {
        return "QualitySettings{" +
                "weather=" + weather +
                ", renderTNTExplosions=" + renderTNTExplosions +
                ", viewDistance=" + viewDistance +
                ", vsync=" + vsync +
                ", particlesMode=" + particlesMode +
                ", graphicsMode=" + graphicsMode +
                ", ao=" + ao +
                ", cloudRenderMode=" + cloudRenderMode +
                ", entityShadows=" + entityShadows +
                ", maxLivingEntities=" + maxLivingEntities +
                ", maxEntityEntities=" + maxEntityEntities +
                ", entitiesDistance=" + entitiesDistance +
                '}';
    }
}
