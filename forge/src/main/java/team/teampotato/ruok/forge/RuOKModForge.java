package team.teampotato.ruok.forge;

import dev.architectury.platform.forge.EventBuses;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.Key.KeyInput;
import team.teampotato.ruok.forge.config.RuOKConfig;

@Mod(RuOKMod.MOD_ID)
@OnlyIn(Dist.CLIENT)
public class RuOKModForge {
    public RuOKModForge() {
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);
        EventBuses.registerModEventBus(RuOKMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(KeyInput.class);
        RuOKMod.init();

    }
}

