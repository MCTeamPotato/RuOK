package team.teampotato.ruok.forge.client;

import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;

import static team.teampotato.ruok.forge.client.MCQuality.*;

public class SettingQuality {


    public static void Setting(QualityMode Mode) {
        if(Mode == QualityMode.CRITICAL) {

            setMCViewDistance(1);//视距
            setMCMaxFps(20);//最大FPS
            setMCVsync(true);//垂直同步
            setMCParticles(ParticlesMode.MINIMAL);//粒子
            setMCGraphicsMode(GraphicsMode.FAST);//画质
            setMCAo(AoMode.OFF);//平滑关照
            setMCCloudRenderMode(CloudRenderMode.OFF);//云渲染
            setMCEntityShadows(false);//实体阴影
        }
        if(Mode == QualityMode.LOW) {

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
