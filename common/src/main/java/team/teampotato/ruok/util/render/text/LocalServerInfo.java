package team.teampotato.ruok.util.render.text;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.text.DecimalFormat;

public class LocalServerInfo {
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.00");
    private static int delay = 0;
    private static Component LOCAL_SERVER_INFO_TEXT = getText();
    public static void refInfo() {
        delay++;//延迟更新
        if(delay > 5) {//作为慢速缓存
            LOCAL_SERVER_INFO_TEXT = getText();
            delay = 0;
        }
    }

    public static Component getLocalServerInfoText() {
        return LOCAL_SERVER_INFO_TEXT;
    }

    @SuppressWarnings("null")
    private static Component getText() {
        IntegratedServer cs = Minecraft.getInstance().getSingleplayerServer();
        double meanTickTime = 0;
        if (cs != null) {
            meanTickTime = mean(cs.getTickTimesNanos()) * 1.0E-6D;
        }
        double meanTPS = Math.min(1000.0/meanTickTime, 20);
        MutableComponent tps = Component.literal(TIME_FORMATTER.format(meanTPS));
        Style style = tps.getStyle();
        if(meanTPS>16) {
            tps.setStyle(style.withColor(ChatFormatting.GREEN));
        } else if (meanTPS>12) {
            tps.setStyle(style.withColor(ChatFormatting.YELLOW));
        } else if (meanTPS>8) {
            tps.setStyle(style.withColor(ChatFormatting.GOLD));
        } else {
            tps.setStyle(style.withColor(ChatFormatting.RED));
        }
        MutableComponent tick = Component.literal(TIME_FORMATTER.format(meanTickTime));
        if(meanTickTime>100) {
            tick.setStyle(style.withColor(ChatFormatting.RED));
        } else if (meanTickTime>50) {
            tick.setStyle(style.withColor(ChatFormatting.GOLD));
        } else if (meanTickTime>30) {
            tick.setStyle(style.withColor(ChatFormatting.YELLOW));
        } else {
            tick.setStyle(style.withColor(ChatFormatting.GREEN));
        }
        return Component.translatable("ruok.options.gui.server.local.ticktime", tick, tps);
    }

    private static long mean(long[] values) {
        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }
}
