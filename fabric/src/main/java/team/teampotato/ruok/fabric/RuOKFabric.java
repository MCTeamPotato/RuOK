package team.teampotato.ruok.fabric;

import net.fabricmc.api.ModInitializer;
import team.teampotato.ruok.RuOKMod;


public class RuOKFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RuOKMod.init();
    }
}
