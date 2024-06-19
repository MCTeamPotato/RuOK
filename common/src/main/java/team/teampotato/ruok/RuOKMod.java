package team.teampotato.ruok;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.function.Supplier;

public class RuOKMod {
    public static final String MOD_ID = "ruokmod";
    public static final String MOD_NAME = "RuOK";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
<<<<<<< Updated upstream
    public static void init() {
        System.out.println(RuOKExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
=======
    private static int ModSize = 0;
    public static void init() {
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);
    }

    public static int getModSize() {
        return ModSize;
    }
    public static void setModSize(int modSize) {
        ModSize = modSize;
>>>>>>> Stashed changes
    }
}
