package team.teampotato.ruok.forge.client.Assessment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.QualityMode;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.client.SettingQuality;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static team.teampotato.ruok.forge.client.Key.KeyInput.minecraftClient;

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
                if (minecraftClient.player == null) return;
                minecraftClient.player.sendMessage(Text.of(
                        I18n.translate("ruok.benchmark.run")
                ));
                startTests();
                RuOK.get().AutoQuality = false;

            }
        }
    }
    /*
    public static void someMethod() {
        // 获取MinecraftClient实例
        MinecraftClient client = MinecraftClient.getInstance();
        // 获取ToastManager对象
        ToastManager toastManager = client.getToastManager();
        // 创建一个自定义的ProgressToast对象，并且设置进度信息为"需要砍树"
        ProgressToast progressToast = new ProgressToast("需要砍树");
        // 使用ToastManager对象的add方法来添加自定义的ProgressToast对象到提示栏中
        toastManager.add(progressToast);
    }

     */

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
            RuOK.get().Max_Rendered_Entities = 64;
            RuOK.get().RenderDistance = 48;
            SendBenchmark();
        }
        if (TotalBenchmarkScore < 600) {
            SettingQuality.Setting(QualityMode.LOW);
            RuOK.get().Max_Rendered_Entities = 128;
            RuOK.get().RenderDistance = 96;
            SendBenchmark();
        }
        if (TotalBenchmarkScore < 800) {
            SettingQuality.Setting(QualityMode.NORMAL);
            RuOK.get().Max_Rendered_Entities = 256;
            RuOK.get().RenderDistance = 128;
            SendBenchmark();
        }
        if (TotalBenchmarkScore < 900) {
            SettingQuality.Setting(QualityMode.HIGH);
            RuOK.get().Max_Rendered_Entities = 512;
            RuOK.get().RenderDistance = 256;
            SendBenchmark();
        }
        if(TotalBenchmarkScore < 1000) {
            SettingQuality.Setting(QualityMode.ULTRA);
            RuOK.get().Max_Rendered_Entities = 1000;
            RuOK.get().RenderDistance = 500;
            SendBenchmark();
        }
    }
    public static void SendBenchmark() {
        if (minecraftClient.player == null) return;
        minecraftClient.player.sendMessage(
                Text.of(I18n.translate("ruok.benchmark.value")
                        + TotalBenchmarkScore
                ));
    }

}
