package team.teampotato.ruok.forge.client.Assessment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.QualityMode;
import team.teampotato.ruok.forge.client.RuOK;
import team.teampotato.ruok.forge.client.SettingQuality;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunBenchmark {
    private static final int MaxRunValue = RuOK.get().MaxRunValue;
    private static int completedTests = 0;
    private static int TotalBenchmarkScore = 0;

    public static void Run() {
        // 使用try-catch语句来捕获可能发生的异常
        MinecraftClient mc = MinecraftClient.getInstance();
        // 获取ClientWorld对象
        ClientWorld world = mc.world;
        if (RuOK.get().AutoQuality) {
            // 判断世界对象是否为空或者是否加载完成
            if (world != null && world.isClient) {
                startTests();
                RuOK.get().AutoQuality = false;
            }
        }
    }

    private static void startTests() {
        completedTests = 0;
        TotalBenchmarkScore = 0;

        // 创建一个定时执行器，每隔30秒运行一次测试
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(RunBenchmark::runSingleBenchmark, 0, RuOK.get().TestTimes, TimeUnit.SECONDS);
    }

    private static void runSingleBenchmark() {
        TotalBenchmarkScore += (int) CPUResult.CalculateCPUScore();
        TotalBenchmarkScore += (int) FPSResult.CalculateFPSScore();
        TotalBenchmarkScore += (int) RAMResult.CalculateRamScore();

        completedTests++;

        if (completedTests >= MaxRunValue) {
            // 执行其他类或操作
            executeOtherOperations();

            // 重置测试次数
            completedTests = 0;
        }
    }

    private static void executeOtherOperations() {
        // 在这里执行其他类或操作
        RuOKMod.LOGGER.info("All benchmarks completed. Total score: " + TotalBenchmarkScore);
        SetQuality();
    }
    public static void SetQuality() {
        if (TotalBenchmarkScore < 300) {
            SettingQuality.Setting(QualityMode.CRITICAL);
        } else if (TotalBenchmarkScore < 600) {
            SettingQuality.Setting(QualityMode.LOW);
        } else if (TotalBenchmarkScore < 900) {
            SettingQuality.Setting(QualityMode.NORMAL);
        } else if (TotalBenchmarkScore < 1200) {
            SettingQuality.Setting(QualityMode.HIGH);
        } else {
            SettingQuality.Setting(QualityMode.ULTRA);
        }
    }

}
