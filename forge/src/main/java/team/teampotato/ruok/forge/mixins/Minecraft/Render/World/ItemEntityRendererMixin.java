package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import team.teampotato.ruok.forge.util.Render.RenderUtil;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.Optional;


@Mixin(ItemEntityRenderer.class)
@Debug

public abstract class ItemEntityRendererMixin extends EntityRenderer<ItemEntity> {


    protected ItemEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Redirect(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lnet/minecraft/util/math/Quaternion;)V"))
    private void onRender(MatrixStack matrixStack, Quaternion quaternion) {
        // 判断是否关闭物品旋转
        if (!RuOK.get().FastItemRender) matrixStack.multiply(quaternion);
    }

    @Redirect(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"))
    private void onRender(MatrixStack matrixStack, double x, double y, double z) {
        // 判断是否关闭物品高低返回
        if (!RuOK.get().FastItemRender) matrixStack.translate(x, y, z);
    }

    @Inject(method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    private void renderItemCount(ItemEntity arg, float g, float h, MatrixStack matrices, VertexConsumerProvider arg3, int l, CallbackInfo ci) {
        if(RuOK.get().RenderDisplayItem) {
            Optional<Pair<Text, String>> itemCountText = RenderUtil.getTotalCountForDisplay(arg);

            if (itemCountText.isPresent()) {
                Pair<Text, String> pair = itemCountText.get();
                Text text = pair.getFirst();
                float f = arg.getHeight() + 0.5F;

                matrices.push();
                matrices.translate(0.0, f, 0.0);
                matrices.multiply(this.dispatcher.getRotation());
                matrices.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();

                int backgroundColor = 0; // 更改为所需的背景色
                float G = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
                int j = (int) (G * 255.0F) << 24;
                TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
                textRenderer.draw(text, -textRenderer.getWidth(text) / 2.0f, 0, backgroundColor, false, matrix4f, arg3, false, j, l);

                matrices.pop();
            }
        }
    }

}
