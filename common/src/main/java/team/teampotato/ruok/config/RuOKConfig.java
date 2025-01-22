package team.teampotato.ruok.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.gui.vanilla.mode.BlockBreakParticleType;
import team.teampotato.ruok.gui.vanilla.mode.QualityType;
import team.teampotato.ruok.gui.vanilla.mode.WeatherType;

import java.util.ArrayList;
import java.util.List;

@Config(name = RuOKMod.MOD_NAME)
public class RuOKConfig implements ConfigData {
    public boolean onCull = true;
    public int MaxEntityEntities = 128;
    public int EntitiesDistance = 64;
    public QualityType qualityModes = QualityType.NORMAL;
    public WeatherType RenderWeather = WeatherType.NORMAL;
    public boolean RenderTNTExplosions = true;
    public boolean FastItemRender = false;
    public boolean RenderDisplayItem = false;
    public List<String> blackListedEntities = new ArrayList<>();
    public List<String> whiteListedEntities = new ArrayList<>();
    public boolean isAlwaysShowItemCount = true;
    public double startTime = 0;
    public boolean chatFix = false;
    public boolean onGui = false;
    public boolean GuiRXTX = false;
    public boolean GuiFPS = false;
    public boolean GuiCPU = false;
    public boolean GuiGPU = false;
    public boolean GuiRAM = false;
    public boolean GuiEntityCount = false;
    public boolean GuiPlayerPos = false;
    public boolean GuiServer = false;
    public boolean GuiCameraTarget = false;
    public boolean Mood = false;
    public boolean Particle = true;
    public BlockBreakParticleType BlockBreakParticleMode = BlockBreakParticleType.HIGH;
    public int MaxParticleDistance = 128;
    public List<String> WhiteListedParticle = new ArrayList<>();
    public List<String> BlackListedParticle = new ArrayList<>();
    public boolean FPSMonitor = true;
    public boolean GuiEasyRamMode = true;
    public boolean GuiDisplayRamUsage = false;
    public int GuiX = 0;
    public int GuiY = 0;
    public boolean EntityRender = true;
    public boolean UseVanillaGui = false;
    public boolean DeBug = false;
    public boolean TextBackground = false;

}
