package team.teampotato.ruok.util;

import net.minecraft.client.*;
import net.minecraft.network.chat.Component;


public class RuOptions {
    private static final Minecraft mi = Minecraft.getInstance();
    private static final Options mcgo = Minecraft.getInstance().options;
    private static Boolean isSendVulkanMessage = false;
    public static void setViewDistance(int value) {
        mcgo.renderDistance().set(value);
    }
    public static void setVsync(boolean value) {//垂直同步
        mcgo.enableVsync().set(value);
    }
    public static void setParticles(ParticleStatus value) {//粒子
        mcgo.particles().set(value);
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
    public static void setGraphicsMode(GraphicsStatus value) {//画质 - WDF RELOAD???
        if(ModLoadState.isVulkanMod() && RuOptions.isSendVulkanMessage.equals(false) && value == GraphicsStatus.FABULOUS) {
            RuOptions.isSendVulkanMessage=true;
            ToastUtil.send(Component.translatable("ruok.options.warn.vulkan.title"),Component.translatable("ruok.options.warn.vulkan.info"));
        }
        mcgo.graphicsMode().set(value);
        mi.levelRenderer.allChanged();
    }
}
