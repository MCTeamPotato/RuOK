package team.teampotato.ruok.forge.client;

import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.config.RuOKConfig;

import static team.teampotato.ruok.forge.client.MCQuality.*;

public class SettingQuality {
    private static final RuOKConfig getConfig = RuOK.get();


    public static void Setting(QualityMode Mode) {
        if(Mode == QualityMode.CRITICAL) {

            RuOK.get().RainRender = false;
            RuOK.get().ThunderRender = false;
            RuOK.get().WeatherNetwork = false;
            RuOK.get().LightingUpdate = false;
            getConfig.RenderMangroveRoots = false;
            getConfig.RenderLeaves = false;
            setMCViewDistance(2);//视距
            setMCMaxFps(20);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.OFF);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.OFF);//云渲染
            setMCEntityShadows(false);//实体阴影
        }
        if(Mode == QualityMode.LOW) {
            RuOK.get().RainRender = false;
            RuOK.get().ThunderRender = false;
            RuOK.get().WeatherNetwork = false;
            RuOK.get().LightingUpdate = true;
            getConfig.RenderMangroveRoots = true;
            getConfig.RenderLeaves = false;
            setMCViewDistance(4);//视距
            setMCMaxFps(30);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.OFF);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.OFF);//云渲染
            setMCEntityShadows(false);//实体阴影
        }
        if (Mode == QualityMode.NORMAL) {
            RuOK.get().RainRender = true;
            RuOK.get().ThunderRender = true;
            RuOK.get().WeatherNetwork = true;
            RuOK.get().LightingUpdate = true;

            getConfig.RenderMangroveRoots = true;
            getConfig.RenderLeaves = true;
            setMCViewDistance(8);//视距
            setMCMaxFps(60);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.MIN);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.FAST);//云渲染
            setMCEntityShadows(true);//实体阴影
        }
        if (Mode == QualityMode.HIGH) {
            RuOK.get().RainRender = true;
            RuOK.get().ThunderRender = true;
            RuOK.get().WeatherNetwork = true;
            RuOK.get().LightingUpdate = true;

            getConfig.RenderMangroveRoots = true;
            getConfig.RenderLeaves = true;
            setMCViewDistance(16);//视距
            setMCMaxFps(120);//最大FPS
            setMCVsync(false);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FANCY);//画质
            setMCAo(AoMode.MAX);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.FANCY);//云渲染
            setMCEntityShadows(true);//实体阴影

        }
        if (Mode == QualityMode.ULTRA) {

            getConfig.RenderMangroveRoots = true;
            getConfig.RenderLeaves = true;
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
