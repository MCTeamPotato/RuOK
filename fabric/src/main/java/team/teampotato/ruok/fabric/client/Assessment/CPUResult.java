package team.teampotato.ruok.fabric.client.Assessment;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CPUResult {
    public static double CalculateCPUScore() {
        double minecraftUsage = getMinecraftCPUUsage();
        double systemUsage = getSystemCPUUsage();

        // 使用差值来计算 CPU 性能得分
        double cpuScore = (systemUsage - minecraftUsage) * 10;

        // 确保得分在0到100之间
        cpuScore = Math.max(0, Math.min(100, cpuScore));
        return cpuScore;
    }

    private static double getMinecraftCPUUsage() {
        // 获取 Minecraft 客户端的 CPU 使用率，具体方法取决于你的操作系统和监测库
        // 返回一个 0 到 1 的值，表示 CPU 使用率

        // 示例：获取 Minecraft 客户端的 CPU 时间
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        return threadMXBean.getThreadCpuTime(Thread.currentThread().getId());
    }

    private static double getSystemCPUUsage() {
        // 获取系统的 CPU 使用率，具体方法取决于你的操作系统和监测库
        // 返回一个 0 到 1 的值，表示系统 CPU 使用率

        // 示例：获取系统的 CPU 使用率
        // 这里需要根据你的操作系统和库来实现
        // 例如，在 Linux 下，你可以读取 /proc/stat 文件来获取 CPU 使用率
        // 在 Windows 下，你可以使用 WMI (Windows Management Instrumentation)
        return 1.0; // 示例中硬编码为 100%
    }
}
