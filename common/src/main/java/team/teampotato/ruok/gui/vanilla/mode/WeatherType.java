package team.teampotato.ruok.gui.vanilla.mode;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.OptionEnum;

import java.util.function.IntFunction;


public enum WeatherType implements OptionEnum {
    CLOSE(0, "ruok.quality.close"),
    LOW(1, "ruok.quality.low"),
    NORMAL(2,"ruok.quality.normal");//Button 2 Name

    private static final IntFunction<WeatherType> BY_ID = ByIdMap.continuous(WeatherType::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String translationKey;

    WeatherType(int id, String translationKey) {
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
            case CLOSE -> {
                return "close";
            }
            case LOW -> {
                return "low";
            }
            case NORMAL -> {
                return  "normal";
            }
            default -> throw new IncompatibleClassChangeError();
        }
    }

    public static WeatherType byId(int id) {
        return BY_ID.apply(id);
    }
}
