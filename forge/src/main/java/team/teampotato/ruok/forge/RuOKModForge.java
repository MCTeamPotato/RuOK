package team.teampotato.ruok.forge;

import org.apache.commons.lang3.tuple.Pair;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.Key.KeyInput;
import team.teampotato.ruok.forge.config.RuOKConfig;

import static team.teampotato.ruok.forge.client.Key.RegisterKey.*;

@Mod(RuOKMod.MOD_ID)
@OnlyIn(Dist.CLIENT)
public class RuOKModForge {
    public RuOKModForge() {
        ModLoadingContext ctx = ModLoadingContext.get();
        ctx.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);
        //EventBuses.registerModEventBus(RuOKMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(KeyInput.class);
        RuOKMod.init();

    }
    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->  {
            ClientRegistry.registerKeyBinding(KeyReduceDistance);
            ClientRegistry.registerKeyBinding(KeyAddDistance);
            ClientRegistry.registerKeyBinding(KeyAddMaxEntities);
            ClientRegistry.registerKeyBinding(KeyReduceMaxEntities);
            ClientRegistry.registerKeyBinding(KeyReloadInvoker);
        });

    }
}

