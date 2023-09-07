package team.teampotato.ruok.fabric;

import team.teampotato.ruok.RuOKExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class RuOKExpectPlatformImpl {
    /**
     * This is our actual method to {@link RuOKExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
