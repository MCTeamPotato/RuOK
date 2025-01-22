package team.teampotato.ruok.gui.vanilla.options;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;
import team.teampotato.ruok.gui.vanilla.options.sop.QualityModeOptions;
import team.teampotato.ruok.gui.vanilla.options.sop.WeatherModeOptions;
import team.teampotato.ruok.util.Render;
import team.teampotato.ruok.vellamo.Score;

import java.util.ArrayList;
import java.util.List;

public class RuOptions {
    private final OptionInstance<WeatherType> weatherMode;
    private final OptionInstance<Integer> entityDistance;
    private final OptionInstance<Integer> entityCount;
    private final OptionInstance<Boolean> onCull;
    private final OptionInstance<QualityType> qualityMode;
    private final OptionInstance<Boolean> runScore;
    private final OptionInstance<Boolean> chatFix;
    private final OptionInstance<Boolean> tntExplosions;
    private final OptionInstance<Boolean> fastItem;
    private final OptionInstance<Boolean> displayItem;
    private final OptionInstance<Boolean> itemCount;

    public RuOptions() {
        this.entityDistance = getEntityDistanceOptions();
        this.weatherMode = WeatherModeOptions.getWeatherModeOptionInstance();
        this.onCull = getOnCullOptions();
        this.entityCount = getEntityCountOptions();
        this.qualityMode = QualityModeOptions.getQualityModeOptionInstance();
        this.runScore = getRunCoreOptions();
        this.chatFix = getChatFixOptions();
        this.tntExplosions = getTNTExplosions();
        this.fastItem = getFastItem();
        this.displayItem = getDisplayItem();
        this.itemCount = getRenderItemCount();
    }

    public OptionInstance<?>[] getOptions() {
        List<OptionInstance<?>> options = new ArrayList<>(List.of());
        options.add(this.getOnCull());//启动剔除
        options.add(this.getEntityDistance());//生物视距
        options.add(this.getEntityCount());//生物数量
        options.add(this.getWeatherMode());//天气设置
        options.add(this.getQualityMode());//全局画质
        options.add(this.getRunScore());//跑分
        options.add(this.getChatFix());//聊天栏修复
        options.add(this.getFastItems());//快速物品
        options.add(this.getDisplayItems());//物品名称
        options.add(this.getTntExplosions());//TNT爆炸粒子
        options.add(this.getItemCount());//物品数量
        return options.toArray(OptionInstance[]::new);
    }


    private static @NotNull OptionInstance<Boolean> getOnCullOptions() {
        return OptionInstance.createBoolean(
                "ruok.quality.cull.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.cull.tooltip")),
                RuOK.get().onCull,
                (value) -> {
                    RuOK.get().onCull = value;
                    RuOK.save();
                });
    }
    private static @NotNull OptionInstance<Boolean> getRunCoreOptions() {
        return OptionInstance.createBoolean(
                "ruok.quality.runscore.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.runscore.tooltip")),
                false,
                (value) -> Score.runVellamo());
    }

    private static @NotNull OptionInstance<Integer> getEntityDistanceOptions() {
        return new OptionInstance<>("ruok.quality.distance.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.distance.tooltip")),
                (optionText, value) -> Options.genericValueLabel(optionText, Component.translatable("ruok.quality.options.block", value)),
                new OptionInstance.IntRange(2, 512),
                RuOK.get().EntitiesDistance,
                (value) -> {
                     RuOK.get().EntitiesDistance = value;
                     RuOK.save();
                });
    }

    private static @NotNull OptionInstance<Integer> getEntityCountOptions() {
        return new OptionInstance<>("ruok.quality.entity.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.entity.tooltip")),
                (optionText, value) -> Options.genericValueLabel(optionText, Component.translatable("ruok.quality.options.entity", value)),
                new OptionInstance.IntRange(8, 1024),
                RuOK.get().MaxEntityEntities,
                (value) -> {
                    RuOK.get().MaxEntityEntities = value;
                    RuOK.save();
                    Render.reloadRenderEntity();
                });
    }
    private static @NotNull OptionInstance<Boolean> getChatFixOptions() {
        return OptionInstance.createBoolean(
                "ruok.quality.chatfix.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.chatfix.tooltip")),
                RuOK.get().chatFix,
                (value) -> {
                    RuOK.get().chatFix=value;
                    RuOK.save();
                });
    }

    private static @NotNull OptionInstance<Boolean> getTNTExplosions() {
        return OptionInstance.createBoolean(
                "ruok.quality.tntexplosions.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.tntexplosions.tooltip")),
                RuOK.get().RenderTNTExplosions,
                (value) -> {
                    RuOK.get().RenderTNTExplosions=value;
                    RuOK.save();
                });
    }

    private static @NotNull OptionInstance<Boolean> getFastItem() {
        return OptionInstance.createBoolean(
                "ruok.quality.fastitem.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.fastitem.tooltip")),
                RuOK.get().FastItemRender,
                (value) -> {
                    RuOK.get().FastItemRender=value;
                    RuOK.save();
                });
    }

    private static @NotNull OptionInstance<Boolean> getDisplayItem() {
        return OptionInstance.createBoolean(
                "ruok.quality.displayitem.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.displayitem.tooltip")),
                RuOK.get().RenderDisplayItem,
                (value) -> {
                    RuOK.get().RenderDisplayItem=value;
                    RuOK.save();
                });
    }

    private static @NotNull OptionInstance<Boolean> getRenderItemCount() {
        return OptionInstance.createBoolean(
                "ruok.quality.itemcount.info",
                (value) -> Tooltip.create(Component.translatable("ruok.quality.itemcount.tooltip")),
                RuOK.get().isAlwaysShowItemCount,
                (value) -> {
                    RuOK.get().isAlwaysShowItemCount=value;
                    RuOK.save();
                });
    }

    public OptionInstance<Integer> getEntityDistance() {
        return entityDistance;
    }
    public OptionInstance<WeatherType> getWeatherMode() {
        return weatherMode;
    }
    public OptionInstance<Boolean> getOnCull() {
        return onCull;
    }

    public OptionInstance<Integer> getEntityCount() {
        return entityCount;
    }

    public OptionInstance<QualityType> getQualityMode() {
        return qualityMode;
    }

    public OptionInstance<Boolean> getRunScore() {
        return runScore;
    }
    public OptionInstance<Boolean> getChatFix() {
        return chatFix;
    }
    public OptionInstance<Boolean> getTntExplosions() {
        return tntExplosions;
    }
    public OptionInstance<Boolean> getItemCount() {
        return itemCount;
    }
    public OptionInstance<Boolean> getFastItems() {
        return fastItem;
    }
    public OptionInstance<Boolean> getDisplayItems() {
        return displayItem;
    }
}
