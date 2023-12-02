package team.teampotato.ruok.fabric.client.Key;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;


public class RegisterKey {
    public static KeyBinding KeyReduceDistance;
    public static KeyBinding KeyAddDistance;
    public static KeyBinding KeyAddMaxEntities;
    public static KeyBinding KeyReduceMaxEntities;
    public static KeyBinding KeyReloadInvoker;
    private static final String KeyGroupRuOK = I18n.translate("ruok.options.pages.ruok.main");
    public static void KeyBind() {

        KeyReduceDistance = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "ruok.key.reducedistance",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KeyGroupRuOK
                )
        );

        KeyAddDistance = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "ruok.key.adddistance",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KeyGroupRuOK
                )
        );

        KeyAddMaxEntities = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "ruok.key.addmaxentities",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KeyGroupRuOK
                )
        );

        KeyReduceMaxEntities = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("ruok.key.reducemaxentities",
                InputUtil.Type.KEYSYM,
                InputUtil.UNKNOWN_KEY.getCode(),
                KeyGroupRuOK
                )
        );
        KeyReloadInvoker = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "ruok.key.reloadinvoker",
                        InputUtil.Type.KEYSYM,
                        InputUtil.UNKNOWN_KEY.getCode(),
                        KeyGroupRuOK
                )
        );
    }





}