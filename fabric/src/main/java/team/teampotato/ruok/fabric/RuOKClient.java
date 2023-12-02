package team.teampotato.ruok.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.fabric.config.RuOKConfig;

import static team.teampotato.ruok.fabric.client.Key.KeyInput.onClientKeyInputTick;
import static team.teampotato.ruok.fabric.client.Key.RegisterKey.KeyBind;
@Environment(EnvType.CLIENT)
public class RuOKClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(RuOKConfig.class, Toml4jConfigSerializer::new);
        onClientKeyInputTick();
        RuOKMod.init();
        KeyBind();
    }
}
