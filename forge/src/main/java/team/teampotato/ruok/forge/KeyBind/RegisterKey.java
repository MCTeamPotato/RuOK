package team.teampotato.ruok.forge.KeyBind;

import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.key.KeyInput;

@Mod.EventBusSubscriber(modid = RuOKMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterKey {
    public static void onRegisterKey() {
        ClientRegistry.registerKeyBinding(KeyInput.KeyAllEntityRender);
        ClientRegistry.registerKeyBinding(KeyInput.KeyReduceDistance);
        ClientRegistry.registerKeyBinding(KeyInput.KeyAddDistance);
        ClientRegistry.registerKeyBinding(KeyInput.KeyAddMaxEntities);
        ClientRegistry.registerKeyBinding(KeyInput.KeyReduceMaxEntities);
        ClientRegistry.registerKeyBinding(KeyInput.KeyReloadInvoker);
    }
}