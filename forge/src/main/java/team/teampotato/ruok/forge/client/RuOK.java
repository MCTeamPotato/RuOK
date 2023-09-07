package team.teampotato.ruok.forge.client;

import me.shedaniel.autoconfig.AutoConfig;
import team.teampotato.ruok.forge.RuOKConfig;


public class RuOK {
    public static void save() {
        AutoConfig.getConfigHolder(RuOKConfig.class).save();
    }
    public static RuOKConfig get() {
        return AutoConfig.getConfigHolder(RuOKConfig.class).getConfig();
    }
    public static void setQualityMode(QualityMode mode) {
        // 从配置文件中读取 QualityModes 的值
        RuOK.get().QualityModes = mode;
        SettingQuality.Setting(mode);
        save();
    }
    public static void setAutoQuality(Boolean mode) {
        // 从配置文件中读取 AutoQuality 的值
        RuOK.get().AutoQuality = mode;
        save();
    }

}
