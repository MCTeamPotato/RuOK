package team.teampotato.ruok.gui.vanilla.mode;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.OptionEnum;

import java.util.function.IntFunction;

public enum QualityType implements OptionEnum {
   // ULTRA(),HIGH,NORMAL,LOW,CRITICAL
    CRITICAL(0, "ruok.quality.close"),
    LOW(1, "ruok.quality.low"),
    NORMAL(2,"ruok.quality.normal"),//Button 2 Name
    HIGH(3,"ruok.quality.high"),
    ULTRA(4,"ruok.quality.ultra");

    private static final IntFunction<QualityType> BY_ID = ByIdMap.continuous(QualityType::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String translationKey;

    QualityType(int id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getKey() {
        return this.translationKey;
    }

    @Override
    public String toString() {
        switch (this) {
            case CRITICAL -> {
                return "close";
            }
            case LOW -> {
                return "low";
            }
            case NORMAL -> {
                return  "normal";
            }
            case HIGH -> {
                return  "high";
            }
            case ULTRA -> {
                return  "ultra";
            }
        }
        return "close";
    }

    public static QualityType byId(int id) {
        return BY_ID.apply(id);
    }
}
