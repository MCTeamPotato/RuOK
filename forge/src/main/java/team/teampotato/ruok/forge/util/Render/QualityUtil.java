package team.teampotato.ruok.forge.util.Render;

import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.config.RuOKConfig;

import static team.teampotato.ruok.forge.util.Render.OptionsUtil.*;

public class QualityUtil {
    private static final RuOKConfig getConfig = RuOK.get();

    public static void Setting(RuOK.qualityMode Mode) {
        if(Mode == RuOK.qualityMode.CRITICAL) {
            getConfig.RenderWeather = RuOK.weather.CLOSE;
            getConfig.RenderTNTExplosions = false;
            setMCViewDistance(2);//视距
            setMCMaxFps(20);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.OFF);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.OFF);//云渲染
            setMCEntityShadows(false);//实体阴影
        }
        if(Mode == RuOK.qualityMode.LOW) {
            getConfig.RenderWeather = RuOK.weather.LOW;
            getConfig.RenderTNTExplosions = false;
            setMCViewDistance(4);//视距
            setMCMaxFps(30);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.OFF);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.OFF);//云渲染
            setMCEntityShadows(false);//实体阴影
        }
        if (Mode == RuOK.qualityMode.NORMAL) {
            getConfig.RenderWeather = RuOK.weather.NORMAL;
            getConfig.RenderTNTExplosions = true;
            setMCViewDistance(8);//视距
            setMCMaxFps(60);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.MIN);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.FAST);//云渲染
            setMCEntityShadows(true);//实体阴影
        }
        if (Mode == RuOK.qualityMode.HIGH) {
            getConfig.RenderWeather = RuOK.weather.NORMAL;
            getConfig.RenderTNTExplosions = true;
            setMCViewDistance(16);//视距
            setMCMaxFps(120);//最大FPS
            setMCVsync(false);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FANCY);//画质
            setMCAo(AoMode.MAX);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.FANCY);//云渲染
            setMCEntityShadows(true);//实体阴影

        }
        if (Mode == RuOK.qualityMode.ULTRA) {
            getConfig.RenderWeather = RuOK.weather.NORMAL;
            getConfig.RenderTNTExplosions = true;
            setMCViewDistance(30);//视距
            setMCMaxFps(300);//最大FPS
            setMCVsync(false);//垂直同步
            setMCParticles(ParticlesMode.ALL);//粒子
            setMCGraphicsMode(GraphicsMode.FABULOUS);//画质
            setMCAo(AoMode.MAX);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.FANCY);//云渲染
            setMCEntityShadows(true);//实体阴影
        }
    }

}
