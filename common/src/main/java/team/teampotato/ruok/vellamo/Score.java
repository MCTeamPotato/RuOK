package team.teampotato.ruok.vellamo;

import com.sun.management.OperatingSystemMXBean;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.mixin.minecraft.accessor.MinecraftAccessor;
import team.teampotato.ruok.util.Quality;
import team.teampotato.ruok.util.ToastUtil;

import java.lang.management.ManagementFactory;

public class Score {
    private static final double cpu;
    private static final double ram;
    static {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        double mem = (double) osmxb.getFreeMemorySize() / osmxb.getTotalMemorySize();
        double memoryLoad = (1-mem)*100; //获取内存
        double cpuLoad = osmxb.getCpuLoad() * 100; //获取CPU
        // 创建 Bean 实例并设置属性
        // 格式化为一位小数
        memoryLoad = Double.parseDouble(String.format("%.1f", memoryLoad));
        cpuLoad = Double.parseDouble(String.format("%.1f", cpuLoad));
        // 创建 Bean 实例并设置属性
        cpu = cpuLoad;
        ram = memoryLoad;
    }
    @Contract(pure = true)
    public static void runVellamo() {
        //获取FPS
        int fps = MinecraftAccessor.getFPS();
        //获取启动时间
        double time = RuOK.get().startTime;
        double score = 0.5 * (100 - cpu) + 0.4 * (100 - ram) + 0.1 * fps + 0.1 * (1000 / time);
        Component level = calculatePerformanceLevel(score);
        ToastUtil.send(Component.translatable("ruok.quality.runscore.info"), Component.translatable("ruok.vellamo.recommend",level));
    }
    private static @NotNull Component calculatePerformanceLevel(double score) {
        if (score > 90) {
            Quality.set(QualityType.ULTRA);
            return Component.translatable("ruok.quality.ultra");
        } else if (score > 70) {
            Quality.set(QualityType.HIGH);
            return Component.translatable("ruok.quality.high");
        } else if (score > 50) {
            Quality.set(QualityType.NORMAL);
            return Component.translatable("ruok.quality.normal");
        } else if (score > 30) {
            Quality.set(QualityType.LOW);
            return Component.translatable("ruok.quality.low");
        } else {
            Quality.set(QualityType.CRITICAL);
            return Component.translatable("ruok.quality.critical");
        }
    }

}
