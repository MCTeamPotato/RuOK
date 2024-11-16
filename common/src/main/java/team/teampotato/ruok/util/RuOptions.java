package team.teampotato.ruok.util;

import net.minecraft.client.*;


public class RuOptions {
    private static final Options mcgo = Minecraft.getInstance().options;
    public static void setViewDistance(int value) {
        mcgo.renderDistance().set(value);
    }
    public static void setVsync(boolean value) {//垂直同步
        mcgo.enableVsync().set(value);
    }
    public static void setParticles(ParticleStatus value) {//粒子
        mcgo.particles().set(value);
    }
    public static void setGraphicsMode(GraphicsStatus value) {//画质
        mcgo.graphicsMode().set(value);
        Minecraft.getInstance().levelRenderer.allChanged();
    }
    public static void setAo(Boolean value) {//平滑关照
        mcgo.ambientOcclusion().set(value);
    }
    public static void setCloudRenderMode(CloudStatus value) {//云渲染
        mcgo.cloudStatus().set(value);
    }
    public static void setEntityShadows(boolean value) {//实体阴影
        mcgo.entityShadows().set(value);
    }
}
