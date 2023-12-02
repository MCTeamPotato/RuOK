package team.teampotato.ruok.fabric.client;

import net.minecraft.text.Text;

public enum QualityMode
{
    ULTRA("Ultra high"),
    HIGH("High"),
    NORMAL("Normal"),
    LOW("Low"),
    CRITICAL("Critical"),
    CUSTOM("Custom");

    private final String name;

    QualityMode(String name) {
        this.name = name;
    }

    public static Text getLocalizedName(QualityMode mode) {
        return Text.literal(mode.name);
    }
}