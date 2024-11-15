package team.teampotato.ruok.gui.sodium;

import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;

public class OptionsStorage {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();
    public static SodiumOptionsStorage getSodiumOpts() {
        return sodiumOpts;
    }
}
