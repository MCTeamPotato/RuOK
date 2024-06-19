package team.teampotato.ruok.forge;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.fml.common.Mod;

import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.config.RuOKConfig;

@Mod(RuOKMod.MOD_ID)
public class RuOKModForge {
    public RuOKModForge() {
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);

    }
}
