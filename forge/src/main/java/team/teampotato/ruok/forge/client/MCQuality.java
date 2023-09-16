package team.teampotato.ruok.forge.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.*;
import team.teampotato.ruok.forge.config.RuOK;


public class MCQuality {

    public static boolean CustomGraphicsQuality = false;

    public MCQuality() {
        if (MCQuality.CustomGraphicsQuality) {
            RuOK.setQualityMode(QualityMode.CUSTOM);
            // 根据需要执行其他操作
        }
    }

    public static void setMCViewDistance(int setValue) {// 视距
        GameOptions options = MinecraftClient.getInstance().options;
        options.viewDistance = setValue;
    }

    public static void setMCMaxFps(int setValue) {// 最大FPS
        GameOptions options = MinecraftClient.getInstance().options;
        options.maxFps = setValue;
    }

    public static void setMCGamma(double setValue) {// 伽马值
        GameOptions options = MinecraftClient.getInstance().options;
        options.gamma = setValue;
    }

    public static void setMCVsync(boolean setValue) {// 垂直同步
        GameOptions options = MinecraftClient.getInstance().options;
        options.enableVsync = setValue;
    }

    public static void setMCParticles(ParticlesMode setValue) {// 粒子
        GameOptions options = MinecraftClient.getInstance().options;
        options.particles = setValue;
    }

    public static void setMCGraphicsMode(GraphicsMode setValue) {// 画质
        GameOptions options = MinecraftClient.getInstance().options;
        options.graphicsMode = setValue;
    }

    public static void setMCAo(AoMode setValue) {// 平滑关照
        GameOptions options = MinecraftClient.getInstance().options;
        options.ao = setValue;
    }

    public static void setMCCloudRenderMode(CloudRenderMode setValue) {// 云渲染
        GameOptions options = MinecraftClient.getInstance().options;
        options.cloudRenderMode = setValue;
    }

    public static void setMCEntityShadows(boolean setValue) {// 实体阴影
        GameOptions options = MinecraftClient.getInstance().options;
        options.entityShadows = setValue;
    }
}
