package team.teampotato.ruok.gui.base;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.util.ModLoadState;
import team.teampotato.ruok.util.Quality;
import team.teampotato.ruok.util.ToastUtil;
import team.teampotato.ruok.util.render.EntityRender;
import team.teampotato.ruok.vellamo.Score;

import java.util.ArrayList;
import java.util.List;

public class BaseManager {
    @Contract(" -> new")
    public static @NotNull BaseUtil getBoolean() {
        return new BaseUtil();
    }
    private static final List<Base<String,?>> binds = new ArrayList<>();
    public static List<Base<String, ?>> getBinds() {
        return binds;
    }
    static {
        BaseManager.register();
        BaseManager.baseListCheck();
    }
    protected static void baseListCheck() {
        //...
    }
    private static void register() {
        BaseManager.getBinds().add(new Base<>("ruok.quality.cull",
                (str, bool) -> {
                    RuOK.get().onCull = bool;
                    RuOK.save();
                },
                (bool) -> RuOK.get().onCull,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.MAIN,
                0
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.runscore",
                (options, value) -> {
                    if(value) Score.runVellamo();
                },
                (options) -> false,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.MAIN,
                1
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.entity",
                (str,integer) -> {
                    RuOK.get().MaxEntityEntities = integer;
                    RuOK.save();
                    EntityRender.reloadRenderEntity();
                },
                (def) -> RuOK.get().MaxEntityEntities,
                BaseUtil.Type.INT,
                BaseUtil.Group.MAIN,
                8,
                8,
                1024,
                "ruok.quality.entity.options",
                2
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.distance",
                (str,integer) -> {
                    RuOK.get().EntitiesDistance = integer;
                    RuOK.save();
                },
                (def) -> RuOK.get().EntitiesDistance,
                BaseUtil.Type.INT,
                BaseUtil.Group.MAIN,
                1,
                4,
                512,
                "ruok.quality.block.options",
                3
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.global",
                (opts, value) -> {
                    Quality.set(value);
                    RuOK.get().qualityModes = value;
                    RuOK.save();
                },
                (opts) -> RuOK.get().qualityModes, // 获取当前配置类中的 qualityMode 字段值
                BaseUtil.Type.ENUM,
                BaseUtil.Group.MAIN,
                new Component[]{
                        Component.translatable("ruok.quality.critical"),
                        Component.translatable("ruok.quality.low"),
                        Component.translatable("ruok.quality.normal"),
                        Component.translatable("ruok.quality.high"),
                        Component.translatable("ruok.quality.ultra")
                },
                3+1
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.fastitem",
                (options, value) -> {
                    RuOK.get().FastItemRender = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().FastItemRender,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                5
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.displayitem",
                (options, value) -> {
                    RuOK.get().RenderDisplayItem = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().RenderDisplayItem,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                6
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.weather",
                (opts, value) -> {
                    RuOK.get().RenderWeather = value;
                    RuOK.save();
                },
                (opts) -> RuOK.get().RenderWeather, // 获取当前配置类中的 qualityMode 字段值
                BaseUtil.Type.ENUM,
                BaseUtil.Group.OTHER,
                new Component[]{
                        Component.translatable("ruok.quality.close"),
                        Component.translatable("ruok.quality.low"),
                        Component.translatable("ruok.quality.normal")
                },
                7
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.itemcount",
                (options, value) -> {
                    RuOK.get().isAlwaysShowItemCount = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().isAlwaysShowItemCount,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                8
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.chatfix",
                (options, value) -> {
                    RuOK.get().chatFix = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().chatFix,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                9
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.mood",
                (options, value) -> {
                    RuOK.get().Mood = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().Mood,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                10
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.particle",
                (options, value) -> {
                    RuOK.get().Particle = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().Particle,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                11
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.particle",
                (opts, value) -> {
                    RuOK.get().BlockBreakParticleMode = value;
                    RuOK.save();
                },
                (opts) -> RuOK.get().BlockBreakParticleMode, // 获取当前配置类中的 qualityMode 字段值
                BaseUtil.Type.ENUM,
                BaseUtil.Group.OTHER,
                new Component[]{
                        Component.translatable("ruok.quality.ultra"),
                        Component.translatable("ruok.quality.high"),
                        Component.translatable("ruok.quality.normal"),
                        Component.translatable("ruok.quality.low"),
                        Component.translatable("ruok.quality.critical"),
                },
                12
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.paricle",
                (str,integer) -> {
                    RuOK.get().MaxParticleDistance = integer;
                    RuOK.save();
                },
                (def) -> RuOK.get().MaxParticleDistance,
                BaseUtil.Type.INT,
                BaseUtil.Group.OTHER,
                1,
                1,
                512,
                "ruok.quality.block.options",
                13
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.on",
                (options, value) -> {
                    RuOK.get().onGui = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().onGui,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                13+1
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.rxtx",
                (options, value) -> {
                    RuOK.get().GuiRXTX = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiRXTX,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                15
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.fps",
                (options, value) -> {
                    RuOK.get().GuiFPS = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiFPS,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                16
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.cpu",
                (options, value) -> {
                    RuOK.get().GuiCPU = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiCPU,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                17
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.gpu",
                (options, value) -> {
                    if(ModLoadState.isVulkanMod()) {
                        ToastUtil.send(
                                "ruok.options.warn.vulkan.title",
                                "ruok.options.warn.vulkan.hud.info"
                        );
                        if(Boolean.TRUE.equals(RuOK.get().GuiGPU)) RuOK.get().GuiGPU = false;
                    } else {
                        RuOK.get().GuiGPU = value;
                        RuOK.save();
                    }

                },
                (options) -> RuOK.get().GuiGPU,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                18
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.ram",
                (options, value) -> {
                    RuOK.get().GuiRAM = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiRAM,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                19
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.entitycount",
                (options, value) -> {
                    RuOK.get().GuiEntityCount = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiEntityCount,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                20
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.playerpos",
                (options, value) -> {
                    RuOK.get().GuiPlayerPos = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiPlayerPos,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                21
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.server",
                (options, value) -> {
                    RuOK.get().GuiServer = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiServer,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                22
        ));

        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.cameratarget",
                (options, value) -> {
                    RuOK.get().GuiCameraTarget = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiCameraTarget,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                23
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.fpsmonitor",
                (options, value) -> {
                    RuOK.get().FPSMonitor = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().FPSMonitor,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.OTHER,
                23+1
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.ram.mode",
                (options, value) -> {
                    RuOK.get().GuiEasyRamMode = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiEasyRamMode,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                25
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.ram.display",
                (options, value) -> {
                    RuOK.get().GuiDisplayRamUsage = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().GuiDisplayRamUsage,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                26
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.quality.entity.render",
                (options, value) -> {
                    RuOK.get().EntityRender = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().EntityRender,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.MAIN,
                27
        ));
        BaseManager.getBinds().add(new Base<>(
                "ruok.options.gui.text.textBackground",
                (options, value) -> {
                    RuOK.get().TextBackground = value;
                    RuOK.save();
                },
                (options) -> RuOK.get().TextBackground,
                BaseUtil.Type.BOOLEAN,
                BaseUtil.Group.HUD,
                28
        ));

    }

}
