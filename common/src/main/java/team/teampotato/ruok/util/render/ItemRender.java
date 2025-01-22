package team.teampotato.ruok.util.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import team.teampotato.ruok.config.RuOK;

import java.util.Optional;

public class ItemRender {
    public static void render(ItemEntity arg, PoseStack poseStack, MultiBufferSource arg3, int l, Quaternionf quaternion) {
        if(RuOK.get().RenderDisplayItem) {
            Optional<Pair<Component, String>> itemCountText = ItemRender.getTotalCountForDisplay(arg);
            if (itemCountText.isPresent()) {
                Pair<Component, String> pair = itemCountText.get();
                Component text = pair.getFirst();
                float f = arg.getBbHeight() + 0.5F;
                poseStack.pushPose();
                poseStack.translate(0.0, f, 0.0);
                poseStack.mulPose(quaternion);
                poseStack.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = poseStack.last().pose();

                int backgroundColor = 0; // 更改为所需的背景色
                int j = 6;
                Font textRenderer = Minecraft.getInstance().font;
                textRenderer.drawInBatch(text,-textRenderer.width(text) / 2.0f, 0.0F, j, false, matrix4f, arg3, Font.DisplayMode.NORMAL, backgroundColor, l);
                poseStack.popPose();
            }
        }
    }
    private static @NotNull Optional<Pair<Component, String>> getTotalCountForDisplay(@NotNull ItemEntity entity) {
        ItemStack stack = entity.getItem();
        int count = stack.getCount();
        String itemName = stack.getDisplayName().getString();
        String displayText = getColorForCount(count) + count + "x " + itemName;

        return !RuOK.get().isAlwaysShowItemCount && count <= stack.getMaxStackSize() ? Optional.of(Pair.of(Component.literal(itemName), itemName)) : Optional.of(Pair.of(Component.literal(displayText), displayText));
    }

    private static String getColorForCount(int count) {
        if (count == 64) return ChatFormatting.RED.toString();
        else if (count > 32) return ChatFormatting.GOLD.toString();
        else if (count > 16) return ChatFormatting.DARK_GREEN.toString();
        else return ChatFormatting.GREEN.toString();
    }
}
