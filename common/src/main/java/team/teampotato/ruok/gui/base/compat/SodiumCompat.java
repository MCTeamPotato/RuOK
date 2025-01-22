package team.teampotato.ruok.gui.base.compat;

import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.gui.base.Base;
import team.teampotato.ruok.gui.sodium.storage.RuOKGameOptions;

import java.util.ArrayList;
import java.util.List;

public class SodiumCompat {
    // 批量适配：将一个 Base 列表转换为 SodiumOptions 兼容的 Base 列表
    public static @NotNull List<Base<RuOKGameOptions, ?>> adaptBaseList(@NotNull List<Base<String, ?>> ob) {
        List<Base<RuOKGameOptions, ?>> ab = new ArrayList<>();
        ob.forEach(e->ab.add(new Base<>(e)));
        return ab;
    }
}
