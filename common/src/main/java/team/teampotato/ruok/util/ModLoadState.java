package team.teampotato.ruok.util;

import java.util.Objects;

public class ModLoadState {
    private static Boolean isEmbeddiumPlus = null;
    public static boolean isLoad(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }
    public static boolean isSodium() {//Sodium Load
        return isLoad("me.jellysquid.mods.sodium.client.SodiumClientMod");
    }
    public static boolean isVulkanMod() {
        return isLoad("net.vulkanmod.vulkan.Vulkan");
    }
    public static boolean isEmbeddiumPlus() {
        return Objects.requireNonNullElse(ModLoadState.isEmbeddiumPlus, false);
    }

    public static void setIsEmbeddiumPlus(boolean isEmbeddiumPlus) {
        ModLoadState.isEmbeddiumPlus = isEmbeddiumPlus;
    }
}
