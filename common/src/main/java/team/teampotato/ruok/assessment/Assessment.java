package team.teampotato.ruok.assessment;

import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.util.QualityUtil;

import java.lang.management.ManagementFactory;

public class Assessment {

    public static void run() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        // 获取 CPU 核心数量
        int cpuCores = processor.getLogicalProcessorCount();

        // 获取 CPU 主频（GHz）
        long[] freqArray = processor.getCurrentFreq();
        double evaluation = getEvaluation(freqArray, cpuCores);

        RuOKMod.LOGGER.info("This assessment: {}", evaluation);

        // 设置画质模式
        setQualityModeBasedOnEvaluation(evaluation);
    }

    private static double getEvaluation(long @NotNull [] freqArray, int cpuCores) {
        double avgFreqGHz = (freqArray.length > 0) ? (freqArray[0] / 1_000_000_000.0) : 2.5; // 默认值为 2.5 GHz

        // 获取 JVM 内存信息（转换为 MB）
        long jvmMemoryBytes = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
        double jvmMemoryMB = jvmMemoryBytes / (1024.0 * 1024.0);

        // 获取 Mod 数量
        int modSize = RuOKMod.getModSize();
        double evaluation;
        if(modSize < 10) evaluation = (cpuCores * 0.2) + (avgFreqGHz * 15) + (jvmMemoryMB * 0.1) ;
        else evaluation = (cpuCores * 0.5) + (avgFreqGHz * 10) + (jvmMemoryMB * 0.5) - (modSize * 0.4);

        return evaluation;
    }

    private static void setQualityModeBasedOnEvaluation(double evaluation) {
        if (evaluation >= 180) {
            QualityUtil.set(QualityUtil.QualityMode.ULTRA,true);
        } else if (evaluation >= 120) {
            QualityUtil.set(QualityUtil.QualityMode.HIGH,true);
        } else if (evaluation >= 80) {
            QualityUtil.set(QualityUtil.QualityMode.NORMAL,true);
        } else if (evaluation >= 40) {
            QualityUtil.set(QualityUtil.QualityMode.LOW,true);
        } else {
            QualityUtil.set(QualityUtil.QualityMode.CRITICAL,true);
        }
       
    }
}
