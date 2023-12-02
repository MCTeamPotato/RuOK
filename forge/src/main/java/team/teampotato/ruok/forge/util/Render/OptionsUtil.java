package team.teampotato.ruok.forge.util.Render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.*;
public class OptionsUtil {
    public static void setMCViewDistance (int setValue) {//视距
        SimpleOption<Integer> ViewDistance = MinecraftClient.getInstance().options.getViewDistance();
        ViewDistance.setValue(setValue);
    }
    public static void setMCMaxFps (int setValue) {//最大FPS
        SimpleOption<Integer> MaxFps = MinecraftClient.getInstance().options.getMaxFps();
        MaxFps.setValue(setValue);
    }
    public static void setMCGamma (double setValue) {//伽马值
        SimpleOption<Double> Gamma = MinecraftClient.getInstance().options.getGamma();
        Gamma.setValue(setValue);
    }
    public static void setMCVsync (boolean setValue) {//垂直同步
        SimpleOption<Boolean> Vsync = MinecraftClient.getInstance().options.getEnableVsync();
        Vsync.setValue(setValue);
    }
    //setMCParticles(ParticlesMode.ALL);
    public static void setMCParticles (ParticlesMode setValue) {//粒子
        SimpleOption<ParticlesMode> Particles = MinecraftClient.getInstance().options.getParticles();
        Particles.setValue(setValue);
    }
    //setMCGraphicsMode(GraphicsMode.FABULOUS);
    public static void setMCGraphicsMode (GraphicsMode setValue) {//画质
        SimpleOption<GraphicsMode> GraphicsMode = MinecraftClient.getInstance().options.getGraphicsMode();
        GraphicsMode.setValue(setValue);
    }
    //setMCAo(AoMode.OFF);
    public static void setMCAo (AoMode setValue) {//平滑关照
        SimpleOption<AoMode> Ao = MinecraftClient.getInstance().options.getAo();
        Ao.setValue(setValue);
    }
    //getMCCloudRenderMode(CloudRenderMode.FANCY);
    public static void setMCCloudRenderMode (CloudRenderMode setValue) {//云渲染
        SimpleOption<CloudRenderMode> CloudRenderMode = MinecraftClient.getInstance().options.getCloudRenderMode();
        CloudRenderMode.setValue(setValue);
        //MinecraftClient.getInstance().options.getCloudRenderModeValue();
    }
    public static void setMCEntityShadows (boolean setValue) {//实体阴影
        SimpleOption<Boolean> EntityShadows = MinecraftClient.getInstance().options.getEntityShadows();
        EntityShadows.setValue(setValue);
    }



}
