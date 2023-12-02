package team.teampotato.ruok.fabric.client.Assessment;

public class RAMResult {
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
}
