package team.teampotato.ruok.forge.util.Render;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraftforge.registries.ForgeRegistries;
import team.teampotato.ruok.forge.config.RuOK;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RenderUtil {

    public static boolean isBlacklisted(Entity entity) {
        // 检查生物是否在黑名单中
        List<String> config = RuOK.get().blackListedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在黑名单中 - 删除渲染
    }

    public static boolean isWhitelisted(Entity entity) {
        // 检查生物是否在白名单中
        List<String> config = RuOK.get().whiteListedEntities;
        String entityName = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType())).toString();
        return config.contains(entityName); // 返回 true 表示在白名单中 - 添加渲染
    }

    public static Optional<Pair<Text, String>> getTotalCountForDisplay(ItemEntity entity) {
        String Red = "§c";
        String Orange = "§6";
        String Green = "§2";
        String LightGreen = "§a";

        ItemStack stack = entity.getStack();
        int total = stack.getCount();
        String itemName = stack.getName().getString(); // 获取物品名称

        String DisplayText;

        if (total == 64) {
            DisplayText = Red + itemName + "x" + total;
        } else if (total > 32) {
            DisplayText = Orange + itemName + "x" + total;
        } else if (total > 16) {
            DisplayText = Green + itemName + "x" + total;
        } else {
            DisplayText = LightGreen + itemName + "x" + total;
        }
        // 创建文本对象
        Text text = Text.of(DisplayText);

        return !RuOK.get().isAlwaysShowItemCount && total <= stack.getMaxCount() ? Optional.empty() : Optional.of(Pair.of(text, DisplayText));
    }



}
