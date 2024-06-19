package team.teampotato.ruok.forge.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.util.QualityUtil;
import team.teampotato.ruok.forge.util.Weather;

import java.util.ArrayList;
import java.util.List;
@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public boolean AutoQuality = false;
    public int maxEntityEntities = 128;
    public int entitiesDistance = 64;
    public QualityUtil.QualityMode QualityModes = QualityUtil.QualityMode.NORMAL;
    public Weather RenderWeather = Weather.NORMAL;
    public boolean RenderTNTExplosions = true;
    public boolean FastItemRender = false;
    public boolean RenderDisplayItem = false;
    public List<String> blackListedEntities = new ArrayList<>();
    public List<String> whiteListedEntities = new ArrayList<>();
    public boolean isAlwaysShowItemCount = true;
}
