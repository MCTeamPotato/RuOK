package team.teampotato.ruok.util;

import java.util.LinkedList;
import java.util.Queue;

public class FPSUtil {
    private static final int MAX_FRAMES = 100; // 队列最大长度
    private static final Queue<Integer> fpsQueue = new LinkedList<>();
    private static int totalFPS = 0; // FPS 的总和
    private static int lastFPS = -1; // 保存上一次记录的 FPS，初始值为 -1（无效值）

    /**
     * 添加当前 FPS 数据并计算平均 FPS
     *
     * @param currentFPS 当前帧率
     */
    public static void addFPS(int currentFPS) {
        if (currentFPS == lastFPS) return; // 如果当前 FPS 和上一次相同，则不记录

        if (fpsQueue.size() >= MAX_FRAMES) {
            // 队列已满时，移除最旧的 FPS
            totalFPS -= fpsQueue.poll();
        }
        // 添加新 FPS 到队列并更新总和
        fpsQueue.add(currentFPS);
        totalFPS += currentFPS;

        // 更新上一次记录的 FPS
        lastFPS = currentFPS;
    }

    /**
     * 获取平均 FPS
     *
     * @return 平均 FPS
     */
    public static double getAverageFPS() {
        if (fpsQueue.isEmpty()) {
            return 0; // 防止除零错误
        }
        return (double) totalFPS / fpsQueue.size();
    }
}
