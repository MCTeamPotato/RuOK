package team.teampotato.ruok.forge.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import team.teampotato.ruok.RuOKMod;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public RuOK.qualityMode QualityModes = RuOK.qualityMode.NORMAL;
    public RuOK.weather RenderWeather = RuOK.weather.NORMAL;
    public Boolean AutoQuality = false;
    public int MaxRunValue = 6;
    public int Max_Rendered_LivingEntities = 64;
    public int Render_Entities_Distance = 32;
    public int Max_Rendered_EntityEntities = 128;
    public int FPS_Expectant = 120;
    public int TestTimes = 30;
    public boolean FastItemRender = false;
    public boolean RenderTNTExplosions = true;//渲染TNT爆炸，默认是渲染
    public boolean RenderLighting = true;//粒子渲染
    public boolean RenderDisplayItem = false;
    public boolean isAlwaysShowItemCount = true;
    @ConfigEntry.Gui.PrefixText
    public List<String> blackListedEntities = new ArrayList<>();
    public List<String> whiteListedEntities = new ArrayList<>();

}
