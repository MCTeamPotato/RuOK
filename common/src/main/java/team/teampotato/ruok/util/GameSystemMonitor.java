package team.teampotato.ruok.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import team.teampotato.ruok.config.RuOK;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameSystemMonitor {
    private static List<String> previousSoundDevices = List.of(); // 保存上一次的设备列表
    private static final Deque<Long> lowFPSHistory = new ArrayDeque<>(); // 记录低 FPS 的时间戳
    private static final int lowFPSThreshold = 30; // 低 FPS 的定义
    private static final Duration oneMinute = Duration.ofSeconds(30); // 统计的时间范围
    private static final double lowFPSRateThreshold = 0.8; // 低 FPS 比例阈值
    private static final Duration toastCooldown = Duration.ofMinutes(5); // 提醒间隔
    private static Instant lastToastTime = Instant.MIN; // 上一次提醒时间
    private static int tickCounter = 0; // 用于控制每秒一次更新逻辑
    private static boolean initState = false;
    private static int delay = 0;


    public static void setInitState(boolean initState) {
        GameSystemMonitor.initState = initState;
    }
    public static boolean getInitState() {
        return GameSystemMonitor.initState;
    }

    public static void runFPSMonitor() {
        if(RuOK.get().FPSMonitor) {
            tickCounter++;

            // 每 20 Tick (1 秒) 执行一次检测逻辑
            if (tickCounter < 20) {
                return;
            }
            tickCounter = 0;

            int currentFPS = OSysInfo.getMinecraft.getFPS();
            long currentTime = System.currentTimeMillis();

            // 记录低 FPS 时间戳
            if (currentFPS < lowFPSThreshold) {
                lowFPSHistory.addLast(currentTime);
            }

            // 清理超过 60 秒的数据
            while (!lowFPSHistory.isEmpty() && currentTime - lowFPSHistory.peekFirst() > oneMinute.toMillis()) {
                lowFPSHistory.pollFirst();
            }

            // 检测低 FPS 比例
            double lowFPSRate = (double) lowFPSHistory.size() / 60; // 每秒检查一次，总计 60 秒窗口
            if (
                    lowFPSRate >= lowFPSRateThreshold &&
                            Duration.between(lastToastTime, Instant.now()).compareTo(toastCooldown) >= 0
            ) {
                // 触发提醒
                ToastUtil.send(
                        Component.translatable("ruok.options.monitor.fps.warn"),
                        Component.translatable("ruok.options.monitor.fps.gosetting")
                );
                lastToastTime = Instant.now(); // 更新最后一次提醒时间
            }
        }
    }
    public static void run() {
        Minecraft mc = Minecraft.getInstance();
        if(mc.level!=null) {
            runFPSMonitor();
        }

        delay++;
        if (delay>8) {
            runSoundDevicesMonitor(mc.getSoundManager());
            delay=0;
        }
    }

    private static void runSoundDevicesMonitor(SoundManager sm) {
        List<String> currentSoundDevices = sm.getAvailableSoundDevices(); // 获取当前设备列表
        // 比较当前设备列表和之前保存的设备列表
        if (!currentSoundDevices.equals(previousSoundDevices)) {
            // 处理当前设备的名称，移除 "OpenAL Soft on " 前缀
            String devices = currentSoundDevices.get(0); // 获取当前设备
            if (devices.startsWith("OpenAL Soft on ")) {
                devices = devices.substring("OpenAL Soft on ".length());
            }
            Component info = Component.translatable("ruok.options.toast.sounddevice.device",devices);
            if(devices.isEmpty()) info = Component.translatable("ruok.options.toast.sounddevice.default");//如果都没有,则返回系统默认
            // 显示提示
            ToastUtil.send(
                    Component.translatable("ruok.options.toast.sounddevice.changed"),
                    info
            );
            // 更新保存的设备列表为当前列表
            previousSoundDevices = currentSoundDevices;
        }
    }
}
