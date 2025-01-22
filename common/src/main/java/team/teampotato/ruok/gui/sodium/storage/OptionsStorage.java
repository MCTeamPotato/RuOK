package team.teampotato.ruok.gui.sodium.storage;

public class OptionsStorage {
    private static final RuOKOptionsStorage optionStorage = new RuOKOptionsStorage();
    public static RuOKOptionsStorage getOptionStorage() {
        return optionStorage;
    }
}