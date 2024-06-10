package team.teampotato.ruok.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.*;

public class Options {
    private static final GameOptions mc = MinecraftClient.getInstance().options;
    public static void setViewDistance(int setValue) {//视距
        mc.getViewDistance().setValue(setValue);
    }
    public static void setVsync(boolean setValue) {//垂直同步
        mc.getEnableVsync().setValue(setValue);
    }
    public static void setParticles(ParticlesMode setValue) {//粒子
        mc.getParticles().setValue(setValue);
    }
    public static void setGraphicsMode(GraphicsMode setValue) {//画质
        mc.getGraphicsMode().setValue(setValue);
    }
    public static void setAo(Boolean setValue) {//平滑关照
        mc.getAo().setValue(setValue);
    }
    public static void setCloudRenderMode(CloudRenderMode setValue) {//云渲染
        mc.getCloudRenderMode().setValue(setValue);
    }
    public static void setEntityShadows(boolean setValue) {//实体阴影
        mc.getEntityShadows().setValue(setValue);
    }
}
