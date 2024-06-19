package team.teampotato.ruok.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.*;

public class Options {
    private static final GameOptions mc = MinecraftClient.getInstance().options;
    public static void setViewDistance(int setValue) {//视距
        mc.viewDistance = setValue;
    }
    public static void setVsync(boolean setValue) {//垂直同步
        mc.enableVsync = setValue;
    }
    public static void setParticles(ParticlesMode setValue) {//粒子
        mc.particles =setValue;
    }
    public static void setGraphicsMode(GraphicsMode setValue) {//画质
        mc.graphicsMode = setValue;
    }
    public static void setAo(AoMode setValue) {//平滑关照
        mc.ao = setValue;
    }
    public static void setCloudRenderMode(CloudRenderMode setValue) {//云渲染
        mc.cloudRenderMode = setValue;
    }
    public static void setEntityShadows(boolean setValue) {//实体阴影
        mc.entityShadows = setValue;
    }
}
