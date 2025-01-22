package team.teampotato.ruok.gui.sodium.storage;

import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class RuOKOptionsStorage implements OptionStorage<RuOKGameOptions> {
    @Override
    public RuOKGameOptions getData() {
        return new RuOKGameOptions();
    }
    @Override
    public void save() {}
}
