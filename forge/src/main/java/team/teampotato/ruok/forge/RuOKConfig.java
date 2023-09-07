package team.teampotato.ruok.forge;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.client.QualityMode;

@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public QualityMode QualityModes = QualityMode.NORMAL;
    public Boolean AutoQuality = false;
    public int MaxRunValue = 6;
    public int Max_Rendered_Entities = 64;
    public int RenderDistance = 32;
    public int FPS_Expectant = 120;
    public int TestTimes = 30;

}
