package team.teampotato.ruok.forge.config;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.QualityMode;

import java.util.ArrayList;
import java.util.List;

@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public QualityMode QualityModes = QualityMode.NORMAL;
    public Boolean AutoQuality = false;
    public int MaxRunValue = 6;
    public int Max_Rendered_LivingEntities = 64;
    public int Render_Entities_Distance = 32;
    public int Max_Rendered_EntityEntities = 128;
    public int FPS_Expectant = 120;
    public int TestTimes = 30;

    public List<String> BlacklistedEntities = new ArrayList<>();
    public List<String> WhitelistedEntities = new ArrayList<>();
    public Boolean RenderLeaves = false;
    public Boolean RenderMangroveRoots = false;
    public boolean WeatherNetwork= true;
    public boolean RainRender = true;
    public boolean ThunderRender = true;
    public boolean LightingUpdate = true;
    public boolean FastItemRender = false;
    public boolean ItemCastShadows = true;//物品阴影

}
