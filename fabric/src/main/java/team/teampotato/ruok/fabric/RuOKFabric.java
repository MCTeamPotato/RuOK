package team.teampotato.ruok.fabric;

import net.fabricmc.api.ModInitializer;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.fabric.keyBind.InputEvent;
import team.teampotato.ruok.fabric.keyBind.RegisterKey;

public class RuOKFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RegisterKey.KeyBind();
        RuOKMod.init();
        InputEvent.onClientKeyInputTick();
    }
}
