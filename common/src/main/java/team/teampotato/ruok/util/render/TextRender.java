package team.teampotato.ruok.util.render;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.config.RuOKConfig;
import team.teampotato.ruok.util.FPSUtil;
import team.teampotato.ruok.util.OSysInfo;
import team.teampotato.ruok.util.render.text.LocalServerInfo;

public class TextRender {
    private static final Minecraft mc = Minecraft.getInstance();
    private static int maxFPS = 0;
    private static Component GPU_TEXT = formatColor((int) Math.min(OSysInfo.getMinecraft.getGPU(), 100));
    private static int delay = 0;
    private static int delayText = 0;
    //private static int delayFast = 0;
    private static Component RAM_TEXT = getRamText();
    private static Component CPU_TEXT = getCPUText();
    private static Component CAMERA_TO_BLOCK = getBlockToPlayerPos();


    public static MutableComponent formatColor(int v) {
        MutableComponent text = Component.literal(String.valueOf(v));
        if (v > 80) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.RED));
        } else if (v > 70) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.YELLOW));
        } else if (v > 30) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GREEN));
        } else {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GREEN));
        }
    }
    public static Component formatFpsColor(int fps) {
        MutableComponent text = Component.literal(String.valueOf(fps)); // 将 FPS 转为文本
        if (fps > 58) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GREEN)); // 流畅
        } else if (fps > (50-2)) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.YELLOW)); // 较流畅
        } else if (fps > 28) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GOLD)); // 勉强流畅
        } else {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.RED)); // 卡顿
        }
    }



    public static Component formatRXTXColor(float v) {
        MutableComponent text = Component.literal(String.valueOf(v));
        if (v > 3000) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.RED));
        } else if (v > 2000) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GOLD));
        } else if (v > 1000) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.YELLOW));
        } else if (v > 500) {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GREEN));
        } else {
            return text.setStyle(text.getStyle().withColor(ChatFormatting.GREEN));
        }
    }
    public static void draw(GuiGraphics context, Minecraft mc) {
        if (RuOK.get().onGui) {
            drawText(context, mc);
        }
    }



    private static void drawText(GuiGraphics context, @NotNull Minecraft mc) {
        if (mc.level != null) {
            Font tr = mc.font;
            int color = 0xFFFFFFFF;
            //!mc.options.debugEnabled不存在
            if (true) {
                RuOKConfig config = RuOK.get();
                int x = config.GuiX != 0 ? config.GuiX : tr.lineHeight / 2;
                int y = config.GuiY != 0 ? config.GuiY : tr.lineHeight / 2;
                //int fh = tr.lineHeight-(tr.lineHeight/3 );
                int fh = tr.lineHeight+4;

                // FPS 信息
                if (RuOK.get().GuiFPS) {
                    int currentFPS = OSysInfo.getMinecraft.getFPS();
                    if (currentFPS > maxFPS) maxFPS = currentFPS; // 更新最大 FPS
                    FPSUtil.addFPS(currentFPS); // 添加当前 FPS 到队列
                    long averageFPS = Math.round(FPSUtil.getAverageFPS());
                    MutableComponent fpsText = Component.translatable("ruok.options.gui.fps", formatFpsColor(currentFPS), formatFpsColor((int) averageFPS), formatFpsColor(maxFPS));

                    // 计算文本的宽度和高度
                    int textWidth = tr.width(fpsText);
                    int textHeight = tr.lineHeight;

                    // 绘制阴影背景
                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, fpsText, x, y, color, true);
                    y += fh;
                }

                // CPU、GPU、RAM、实体数
                if (RuOK.get().GuiCPU || RuOK.get().GuiRAM || RuOK.get().GuiEntityCount || RuOK.get().GuiGPU) {
                    MutableComponent info = Component.empty();
                    boolean addSeparator = false;

                    if (RuOK.get().GuiCPU) {
                        info.append(Component.translatable("ruok.options.gui.cpu", CPU_TEXT));
                        addSeparator = true;
                    }
                    if (RuOK.get().GuiGPU) {
                        if (addSeparator) {
                            info.append(Component.literal(" | "));
                        }
                        info.append(Component.translatable("ruok.options.gui.gpu", GPU_TEXT));

                        addSeparator = true;
                    }
                    if (RuOK.get().GuiRAM) {
                        if (addSeparator) {
                            info.append(Component.literal(" | "));
                        }
                        info.append(Component.translatable("ruok.options.gui.mem", RAM_TEXT));
                        addSeparator = true;
                    }
                    if (RuOK.get().GuiEntityCount) {
                        if (addSeparator) {
                            info.append(Component.literal(" | "));
                        }
                        info.append(getEntityCount(mc.level));
                    }

                    // 计算文本的宽度和高度
                    int textWidth = tr.width(info);
                    int textHeight = tr.lineHeight;

                    // 绘制阴影背景
                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, info, x, y, color, true);
                    y += fh;
                }

                // 玩家位置
                if (RuOK.get().GuiPlayerPos && mc.player != null) {
                    LocalPlayer p = mc.player;
                    MutableComponent posText = Component.translatable(
                            "ruok.options.gui.player.pos",
                            dis(p.getX()),
                            dis(p.getY()),
                            dis(p.getZ())
                    );

                    // 计算文本的宽度和高度
                    int textWidth = tr.width(posText);
                    int textHeight = tr.lineHeight;

                    // 绘制阴影背景
                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, posText, x, y, color, true);
                    y += fh;
                }

                // 服务器信息
                if (RuOK.get().GuiServer) {
                    Component serverText = mc.getSingleplayerServer() != null
                            ? LocalServerInfo.getLocalServerInfoText() // 本地服务器
                            : getRemoteServerPing(mc); // 远程服务器

                    // 计算文本的宽度和高度
                    int textWidth = tr.width(serverText);
                    int textHeight = tr.lineHeight;

                    // 绘制阴影背景
                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, serverText, x, y, color, true);
                    y += fh;
                }

                // 相机射线
                if (RuOK.get().GuiCameraTarget) {
                    // 计算文本的宽度和高度
                    int textWidth = tr.width(CAMERA_TO_BLOCK);
                    int textHeight = tr.lineHeight;

                    // 绘制阴影背景
                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, CAMERA_TO_BLOCK, x, y, color, true);
                    y += fh;
                }

                // 上传和下载流量
                if (RuOK.get().GuiRXTX) {
                    Component serverIOText = getRXTXData(mc);

                    // 计算文本的宽度和高度
                    int textWidth = tr.width(serverIOText);
                    int textHeight = tr.lineHeight;

                    addTextShadow(context, x, y, textWidth, textHeight);

                    // 绘制文本
                    context.drawString(tr, serverIOText, x, y, color, true);
                    // y += fh; // 不增加y以防止文本间隔过大
                }
            }
        }
    }

    private static void addTextShadow(GuiGraphics context, int x, int y, int textWidth, int textHeight) {
        if(RuOK.get().TextBackground) {
            int shadowOffset = 2; // 阴影偏移量
            context.fill(x - shadowOffset, y - shadowOffset, x + textWidth + shadowOffset, y + textHeight + shadowOffset, -1873784752);
        }
    }


    private static Component getCPUText() {
        int cpuLoad = (int) OSysInfo.getSystem.getCpuLoad();
        return formatColor(cpuLoad);
    }
    private static Component getRamText() {
        if (RuOK.get().GuiEasyRamMode) {
            return handleEasyRamMode();
        }
        return handleNormalRamMode();
    }

    private static Component handleEasyRamMode() {
        if (RuOK.get().GuiDisplayRamUsage) {
            return displayRamUsage().append(" ").append(formatColor(OSysInfo.getSystem.getMemoryUsagePercentage())).append("%");
        }
        return formatColor(OSysInfo.getSystem.getMemoryUsagePercentage()).append("%");
    }

    private static Component handleNormalRamMode() {
        if (RuOK.get().GuiDisplayRamUsage) {
            return displayRamUsage().append(" ").append(formatRamInfo());
        }
        return formatRamInfo();
    }

    private static Component formatRamInfo() {
        MutableComponent text = Component.empty();
        text.append(formatByte(OSysInfo.getSystem.getUsedRam()));
        text.append("/");
        text.append(formatByte(OSysInfo.getSystem.getMaxRam()));
        return text;
    }
    private static String formatByte(double bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            double kb = bytes / 1024;
            // 保留两位小数
            return String.format("%.2f KB", kb);
        } else if (bytes < 1024 * 1024 * 1024) {
            double mb = bytes / (1024 * 1024);
            // 保留两位小数
            return String.format("%.2f MB", mb);
        } else {
            double gb = bytes / (1024 * 1024 * 1024);
            // 保留两位小数
            return String.format("%.2f GB", gb);
        }
    }

    public static MutableComponent displayRamUsage() {
        MutableComponent text = Component.empty();
        int def = 10; // 总共显示 10 个格子，每个格子代表 10% 内存使用
        int usagePercentage = OSysInfo.getSystem.getMemoryUsagePercentage();
        int numBars = usagePercentage / 10; // 每个竖线代表 10% 的内存使用

        // 确保不会超过最大数量（即 10 个格子）
        if (numBars > def) {
            numBars = def;
        }

        MutableComponent bar = Component.empty().append("[");

        // 根据使用比例显示竖线或空格，并且添加渐变色
        for (int i = 0; i < def; i++) {
            if (i < numBars) { // 显示竖线
                float ratio = (float) i / def; // 计算当前竖线的位置比例，0 到 1 之间

                // 根据比例决定颜色的渐变
                Style style;
                if (ratio < 0.30) {
                    style = text.getStyle().withColor(ChatFormatting.GREEN); // 前 30% 使用绿色
                } else if (ratio < 0.60) {
                    style = text.getStyle().withColor(ChatFormatting.YELLOW); // 中低 30% 使用黄色
                } else if (ratio < 0.85) {
                    style = text.getStyle().withColor(ChatFormatting.GOLD); // 中高 25% 使用金色
                } else {
                    style = text.getStyle().withColor(ChatFormatting.RED); // 后 25% 使用红色
                }

                // 使用更细的竖线效果，与空格宽度相似
                bar.append(Component.literal("|").setStyle(style));
            } else { // 显示空格
                bar.append(" "); // 空格的宽度
            }
        }

        bar.append("]");
        return bar;
    }



    //TODO refInfo
    public static void refInfo() {
        LocalServerInfo.refInfo();
        delay++;//延迟更新
        delayText++;
       // delayFast++;
        if(delay > 20) {//作为慢速缓存
            GPU_TEXT = formatColor((int) Math.min(OSysInfo.getMinecraft.getGPU(), 100));
            delay = 0;
        }
        if(delayText > 10) {//更快的
            RAM_TEXT = getRamText();
            CPU_TEXT = getCPUText();
            delayText = 0;
        }
        CAMERA_TO_BLOCK = getBlockToPlayerPos();
//        if(delayFast == 0) {
//            delayFast = 0;
//        }
    }

    private static @NotNull Component getRXTXData(@NotNull Minecraft mc) {
        if(mc.getConnection()!=null) {
            Connection clientConnection = mc.getConnection().getConnection();
            float rx = clientConnection.getAverageReceivedPackets(); // 接收 (Server -> Client)
            float tx = clientConnection.getAverageSentPackets();     // 发送 (Client -> Server)
            // 格式化输出
            return Component.translatable("ruok.options.gui.server,remote.dataio", formatRXTXColor(Math.round(tx)),formatRXTXColor(Math.round(rx)));
        } else return Component.empty();

    }
    private static String dis(double I) {
        return String.format("%.1f", I);
    }
    @Contract(value = "_ -> new", pure = true)
    private static @NotNull Component getRemoteServerPing(@NotNull Minecraft mc) {
        if (mc.player != null && mc.getConnection() != null) {
            ClientPacketListener cNet = mc.getConnection();
            PlayerInfo entry = cNet.getPlayerInfo(mc.player.getUUID());
            if (entry != null && cNet.getServerData()!=null) {
                String mcs = cNet.getServerData().ip;
                return Component.translatable("ruok.options.gui.server.remote.info",mcs,entry.getLatency());
            }

        }
        return Component.empty();
    }


    private static @NotNull Component getEntityCount(@NotNull ClientLevel world) {
        return Component.translatable("ruok.options.gui.entity.count",formatColor(world.getEntityCount()));
    }

    private static Component getBlockToPlayerPos() {
        double md = 300.0;
        Entity ce = TextRender.mc.getCameraEntity();
        if (ce != null && TextRender.mc.level != null) {
            // 获取相机的射线检测结果
            HitResult bH = ce.pick(md, 1.0F, false);

            if (bH.getType() == HitResult.Type.BLOCK) { // 确保击中的是方块
                Vec3 bP = bH.getLocation(); // 方块位置
                Vec3 pP = ce.position(); // 玩家位置

                // 计算距离
                double distance = Math.sqrt(
                        Math.pow(bP.x - pP.x, 2) +
                                Math.pow(bP.y - pP.y, 2) +
                                Math.pow(bP.z - pP.z, 2)
                );

                // 返回带距离的文本
                return Component.translatable("ruok.options.gui.camera.pos",
                        dis(bP.x),
                        dis(bP.y),
                        dis(bP.z),
                        dis(distance)
                );
            } else {
                return Component.translatable("ruok.options.gui.camera.no_block",md);
            }
        } else {
            return Component.empty();
        }
    }
    public static int fadeColor(int originalColor, float alphaFactor, float rgbFactor) {
        int alpha = (int) (((originalColor >> 24) & 0xFF) * alphaFactor);
        int rgb = originalColor & 0x00FFFFFF;
        int newR = (int) ((rgb >> 16 & 0xFF) * rgbFactor);
        int newG = (int) ((rgb >> 8 & 0xFF) * rgbFactor);
        int newB = (int) ((rgb & 0xFF) * rgbFactor);
        return (alpha << 24) | (newR << 16) | (newG << 8) | newB;
    }

}
