package team.teampotato.ruok.util.clazz;

import team.teampotato.ruok.config.RuOK;

import java.lang.management.ManagementFactory;

public class StartTime {
    private static boolean startUp = true;
    public static void start() {
        //将启动时间保存到配置内(Double)
        if (!startUp) return;
        RuOK.get().startTime = ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0;
        RuOK.save();
        startUp = false;
    }
}
