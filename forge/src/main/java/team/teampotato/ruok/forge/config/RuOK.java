package team.teampotato.ruok.forge.config;

import me.shedaniel.autoconfig.AutoConfig;
import team.teampotato.ruok.forge.client.QualityMode;
import team.teampotato.ruok.forge.client.SettingQuality;


public interface RuOK {
    static void save() {
        AutoConfig.getConfigHolder(RuOKConfig.class).save();
    }
    static RuOKConfig get() {
        return AutoConfig.getConfigHolder(RuOKConfig.class).getConfig();
    }
    static void setQualityMode(QualityMode mode) {
        // 从配置文件中读取 QualityModes 的值
        RuOK.get().QualityModes = mode;
        SettingQuality.Setting(mode);
        save();
    }
    static void setAutoQuality(Boolean mode) {
        // 从配置文件中读取 AutoQuality 的值
        RuOK.get().AutoQuality = mode;
        save();
    }

}
