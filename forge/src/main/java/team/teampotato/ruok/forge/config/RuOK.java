package team.teampotato.ruok.forge.config;

import me.shedaniel.autoconfig.AutoConfig;

public interface RuOK {
    static void save() {
        AutoConfig.getConfigHolder(RuOKConfig.class).save();
    }
    static RuOKConfig get() {
        return AutoConfig.getConfigHolder(RuOKConfig.class).getConfig();
    }
}
