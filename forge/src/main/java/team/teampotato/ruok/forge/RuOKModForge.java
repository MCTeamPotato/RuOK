package team.teampotato.ruok.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.KeyBind.InputEvent;

import static team.teampotato.ruok.RuOKMod.MOD_ID;


@Mod(MOD_ID)
@OnlyIn(Dist.CLIENT)
public class RuOKModForge {

    public RuOKModForge() {
        init();
        MinecraftForge.EVENT_BUS.register(InputEvent.class);
    }
    private static void init() {
        RuOKMod.init();
        RuOKMod.setModSize(ModList.get().getMods().size());
    }

}

