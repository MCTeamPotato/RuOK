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
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));

    public static void init() {
        
        System.out.println(RuOKExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
