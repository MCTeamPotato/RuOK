package team.teampotato.ruok;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.teampotato.ruok.config.RuOKConfig;

public class RuOKMod {
    public static final String MOD_ID = "ruokmod";
    public static final String MOD_NAME = "RuOK";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
<<<<<<< Updated upstream
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));

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
