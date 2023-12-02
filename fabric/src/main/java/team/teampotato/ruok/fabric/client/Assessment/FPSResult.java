package team.teampotato.ruok.fabric.client.Assessment;

import team.teampotato.ruok.fabric.config.RuOK;
import team.teampotato.ruok.fabric.mixins.Minecraft.FrameRateTracker;

public class FPSResult {
    public static double CalculateFPSScore() {
        double FPS = FrameRateTracker.getFPS();
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
}
