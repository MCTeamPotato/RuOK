package team.teampotato.ruok.forge;

import net.minecraftforge.fml.loading.FMLPaths;
import team.teampotato.ruok.RuOKExpectPlatform;

import java.nio.file.Path;

public class RuOKExpectPlatformImpl {
    /**
     * This is our actual method to {@link RuOKExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
