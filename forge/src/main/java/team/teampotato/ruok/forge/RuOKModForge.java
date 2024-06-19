package team.teampotato.ruok.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.forge.KeyBind.InputEvent;
import team.teampotato.ruok.forge.KeyBind.RegisterKey;

@Mod(RuOKMod.MOD_ID)
public class RuOKModForge {
    public RuOKModForge() {
        init();
        MinecraftForge.EVENT_BUS.register(InputEvent.class);
    }
    private static void init() {
        RegisterKey.onRegisterKey();
        RuOKMod.init();
        RuOKMod.setModSize(ModList.get().getMods().size());
    }
}
