package team.teampotato.ruok.key;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;


public class KeyInput {

    // 创建一个按键绑定
    private static final String KeyGroupRuOK = I18n.translate("ruok.options.pages.ruok.main");
    public static final KeyBinding KeyAllEntityRender = new KeyBinding(
            I18n.translate("ruok.key.allentityrender"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );

    public static final KeyBinding KeyReduceDistance = new KeyBinding(
            I18n.translate("ruok.key.reducedistance"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );

    public static KeyBinding KeyAddDistance = new KeyBinding(
            I18n.translate("ruok.key.adddistance"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyAddMaxEntities = new KeyBinding(
            I18n.translate("ruok.key.addmaxentities"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyReduceMaxEntities = new KeyBinding(
            I18n.translate("ruok.key.reducemaxentities"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );
    public static KeyBinding KeyReloadInvoker = new KeyBinding(
            I18n.translate("ruok.key.reloadinvoker"),
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            KeyGroupRuOK
    );

}
