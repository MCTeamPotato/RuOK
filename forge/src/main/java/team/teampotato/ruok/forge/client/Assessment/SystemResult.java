package team.teampotato.ruok.forge.client.Assessment;

import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.mixins.Minecraft.Accessor.MinecraftClientAccessor;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class SystemResult {
    public static double CalculateRamScore() {
        Runtime runtime = Runtime.getRuntime();
        long MinecraftRam = ((runtime.maxMemory() / (1024 * 1024)) - ((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)));

        // 使用差值来计算 CPU 性能得分
        //double RAMScore = (MinecraftMaxRam - MinecraftRam);
        long RAM = MinecraftRam / 100;
        long RAMScore = 100 - RAM;

        // 确保得分在0到100之间
        RAMScore = Math.max(0, Math.min(100, RAMScore));

        return RAMScore;
    }
    public static double CalculateFPSScore() {
        double FPS = MinecraftClientAccessor.getFPS();
        double Expectant = RuOK.get().FPS_Expectant;
        double FPSScore;
        // 使用差值来计算 CPU 性能得分
        if (FPS > RuOK.get().FPS_Expectant) {
            FPSScore = 100;
        } else {
            FPSScore = (Expectant - FPS);
        }

        // 确保得分在0到100之间
        FPSScore = Math.max(0, Math.min(100, FPSScore));
        return FPSScore;
    }
    public static double CalculateCPUScore() {
        double minecraftUsage = getMinecraftCPUUsage();
        double systemUsage = 1.0;

        // 使用差值来计算 CPU 性能得分
        double cpuScore = (systemUsage - minecraftUsage) * 10;

        // 确保得分在0到100之间
        cpuScore = Math.max(0, Math.min(100, cpuScore));
        return cpuScore;
    }

    private static double getMinecraftCPUUsage() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        return threadMXBean.getThreadCpuTime(Thread.currentThread().getId());
    }


}

