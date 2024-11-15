package team.teampotato.ruok;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.teampotato.ruok.config.RuOKConfig;

public class RuOKMod {
    public static final String MOD_ID = "ruokmod";
    public static final String MOD_NAME = "RuOK";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static boolean isEmbeddiumLoad = false;
    public static void init() {
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);
    }
    public static String getRegisterName(EntityType<?> entity) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity).toString();

    }


    public static boolean getEmbeddiumLoad() {
        return isEmbeddiumLoad;
    }
    public static void setEmbeddiumLoad(boolean modSize) {
        isEmbeddiumLoad = modSize;
    }
}
