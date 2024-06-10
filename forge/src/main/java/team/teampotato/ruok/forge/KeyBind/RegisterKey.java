package team.teampotato.ruok.forge.KeyBind;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.key.KeyInput;

@Mod.EventBusSubscriber(modid = RuOKMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterKey {

    @SubscribeEvent
    public static void onRegisterKey(@NotNull RegisterKeyMappingsEvent event) {
        event.register(KeyInput.KeyAllEntityRender);
        event.register(KeyInput.KeyReduceDistance);
        event.register(KeyInput.KeyAddDistance);
        event.register(KeyInput.KeyAddMaxEntities);
        event.register(KeyInput.KeyReduceMaxEntities);
        event.register(KeyInput.KeyReloadInvoker);
    }
}