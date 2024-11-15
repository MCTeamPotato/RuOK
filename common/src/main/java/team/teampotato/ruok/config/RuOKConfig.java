package team.teampotato.ruok.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.gui.vanilla.mode.QualityMode;
import team.teampotato.ruok.gui.vanilla.mode.WeatherMode;

import java.util.ArrayList;
import java.util.List;
@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public boolean onCull = true;
    public int maxEntityEntities = 128;
    public int entitiesDistance = 64;
    public QualityMode QualityModes = QualityMode.NORMAL;
    public WeatherMode RenderWeather = WeatherMode.NORMAL;
    public boolean RenderTNTExplosions = true;
    public boolean FastItemRender = false;
    public boolean RenderDisplayItem = false;
    public List<String> blackListedEntities = new ArrayList<>();
    public List<String> whiteListedEntities = new ArrayList<>();
    public boolean isAlwaysShowItemCount = true;
    public double startTime = 0;
    public boolean chatFix = false;
}
