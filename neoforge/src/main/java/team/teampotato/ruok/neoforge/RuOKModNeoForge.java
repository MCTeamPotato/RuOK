package team.teampotato.ruok.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.Mod;
import team.teampotato.ruok.RuOKMod;


import static team.teampotato.ruok.RuOKMod.MOD_ID;


@Mod(MOD_ID)
@OnlyIn(Dist.CLIENT)
public class RuOKModNeoForge {
    public RuOKModNeoForge() {
        RuOKMod.init();
    }

}

