package team.teampotato.ruok.forge.client.Key;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.common.Mod;
import team.teampotato.ruok.RuOKMod;


@Mod.EventBusSubscriber(modid = RuOKMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterKey {
    private static final String KeyGroupRuOK = I18n.translate("ruok.options.pages.ruok");
    public static final KeyBinding KeyReduceDistance = new KeyBinding(
            I18n.translate("ruok.key.reducedistance"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );

    public static KeyBinding KeyAddDistance = new KeyBinding(
            I18n.translate("ruok.key.adddistance"),
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyAddMaxEntities = new KeyBinding(
            I18n.translate("ruok.key.addmaxentities"),
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyReduceMaxEntities = new KeyBinding(
            I18n.translate("ruok.key.reducemaxentities"),
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyReloadInvoker = new KeyBinding(
            I18n.translate("ruok.key.reloadinvoker"),
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );


/*
    @SubscribeEvent
    public static void onRegisterKey(RegisterKeyMappingsEvent event) {
        event.register(KeyReduceDistance);
        event.register(KeyAddDistance);
        event.register(KeyAddMaxEntities);
        event.register(KeyReduceMaxEntities);
        event.register(KeyReloadInvoker);
    }

 */
}