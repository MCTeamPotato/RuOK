package team.teampotato.ruok.util;

import com.sun.management.OperatingSystemMXBean;
import net.minecraft.client.Minecraft;

import java.lang.management.ManagementFactory;

public class OSysInfo {
    public interface getSystem {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        static int getMemoryUsagePercentage() {
            Runtime runtime = Runtime.getRuntime();
            // 最大可用内存
            long maxMemory = runtime.maxMemory();
            // 当前使用的内存 (总内存 - 空闲内存)
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            // 计算占用百分比
            return (int) ((usedMemory * 100) / maxMemory);
        }
        static double getMaxRam() {
            Runtime runtime = Runtime.getRuntime();
            return runtime.maxMemory();
        }
        static double getUsedRam() {
            Runtime runtime = Runtime.getRuntime();
            return runtime.totalMemory() - runtime.freeMemory();
        }
        static OperatingSystemMXBean getOsmxb() {
            return osmxb;
        }
        static double getCpuLoad() {
            return osmxb.getCpuLoad() * 100; //获取CPU
        }
    }
    public static class getMinecraft {
        private static final Minecraft mc = Minecraft.getInstance();
        public static int getFPS() {
            return mc.getFps();
        }
        public static double getGPU() {
            return mc.getGpuUtilization();
        }
        public static Minecraft c() {
            return mc;
        }

    }

}
