package team.teampotato.ruok.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.NotNull;

public class ChatFix {
    public static int getOffset(Minecraft client) {
        // 获取Minecraft客户端实例和玩家实例
        LocalPlayer player = client.player;

        // 如果玩家不存在、是创造模式或观察者模式，则返回0
        if (player == null || player.isCreative() || player.isSpectator()) {
            return 0;
        }


//        // 获取玩家的健康值（注意：这里应该是浮点数，因为健康值可以有小数）
//        float health = player.getHealth();
//
//        // 初始化偏移量
//        int offset = 0;
//
//        // 如果玩家穿戴了盔甲（这里假设只要盔甲值大于0就算穿戴了盔甲）
//        if (player.getArmor() > 0) {
//            offset += 10; // 穿戴盔甲时基础增加10点偏移量
//        }
//
//        // 计算额外的健康值增量（超过默认20颗心的部分）
//        int extraHealth = (int) Math.max(0, health - 20 * 2); // 20颗心等于40点健康值
//        int increments = extraHealth / 20; // 每额外20点健康值增加一次偏移量
//        offset += increments * 10; // 每次额外增加10点偏移量
//
//        // 但是，由于我们想要确保默认健康值的玩家也至少有一点偏移量（如果穿了盔甲的话）
//        // 并且上面的逻辑已经为穿戴盔甲的玩家增加了基础偏移量
//        // 因此，这里的increments为0时不会影响已经因为盔甲而增加的偏移量
//
//        // 获取窗口高度的1/20作为最大偏移量限制（如果需要的话）
//         int maxHeightOffset = getWindowHeight(client);
//         if (offset > maxHeightOffset) {
//             offset = maxHeightOffset;
//         }
         //return offset;
         if(player.getArmorValue()>1) return 10; else return 0;
    }

    // 获取窗口高度的1/20的方法
    public static int getWindowHeight(@NotNull Minecraft client) {
        int height = client.getWindow().getHeight();
        return height / 20;
    }

}
