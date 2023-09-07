package team.teampotato.ruok.fabric;

import team.teampotato.ruok.RuOKMod;
import net.fabricmc.api.ModInitializer;

public class RuOKModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RuOKMod.init();
    }
}
