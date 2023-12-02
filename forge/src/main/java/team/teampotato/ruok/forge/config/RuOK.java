package team.teampotato.ruok.forge.config;

import me.shedaniel.autoconfig.AutoConfig;
import team.teampotato.ruok.forge.util.Render.QualityUtil;


public interface RuOK {
    static void save() {
        AutoConfig.getConfigHolder(RuOKConfig.class).save();
    }
    static RuOKConfig get() {
        return AutoConfig.getConfigHolder(RuOKConfig.class).getConfig();
    }

    static void setQualityMode(qualityMode mode) {
        // 从配置文件中读取 QualityModes 的值
        RuOK.get().QualityModes = mode;
        QualityUtil.Setting(mode);
        save();
    }
    static void setAutoQuality(Boolean mode) {
        // 从配置文件中读取 AutoQuality 的值
        RuOK.get().AutoQuality = mode;
        save();
    }
    enum weather {
        NORMAL,
        LOW,
        CLOSE
    }
    enum qualityMode {
        ULTRA,
        HIGH,
        NORMAL,
        LOW,
        CRITICAL,
        CUSTOM;


    }

}
