package team.teampotato.ruok.mixin.minecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.util.Render;

import java.util.Optional;


@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin extends EntityRenderer<ItemEntity> {
    protected ItemEntityRendererMixin(EntityRendererProvider.Context ctx) {
        super(ctx);
    }
    @Inject(method = "getRenderedAmount", at = @At("HEAD"), cancellable = true)
    private static void onRenderItem(int i, CallbackInfoReturnable<Integer> cir){
        if(RuOK.get().FastItemRender) cir.setReturnValue(1);
    }

    @Redirect(method = "render*", at = @At(value = "INVOKE", target ="Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V"))
    private void onRender(PoseStack matrixStack, Quaternionf quaternion) {
        // 判断是否关闭物品旋转
        if (!RuOK.get().FastItemRender) matrixStack.mulPose(quaternion);
    }

    @Redirect(method = "render*", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"))
    private void onRender(PoseStack matrixStack, float x, float y, float z) {
        // 判断是否关闭物品高低返回
        if (!RuOK.get().FastItemRender) matrixStack.translate(x, y, z);
    }

    @Inject(method = "Lnet/minecraft/client/renderer/entity/ItemEntityRenderer;render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    private void renderItemCount(ItemEntity arg, float g, float h, PoseStack matrices, MultiBufferSource arg3, int l, CallbackInfo ci) {
        if(RuOK.get().RenderDisplayItem) {
            Optional<Pair<Component, String>> itemCountText = Render.getTotalCountForDisplay(arg);

            if (itemCountText.isPresent()) {
                Pair<Component, String> pair = itemCountText.get();
                Component text = pair.getFirst();
                float f = arg.getBbHeight() + 0.5F;

                matrices.pushPose();
                matrices.translate(0.0, f, 0.0);
                matrices.mulPose(this.entityRenderDispatcher.cameraOrientation());
                matrices.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrices.last().pose();

                int backgroundColor = 0; // 更改为所需的背景色
                int j = 6;
                Font textRenderer = Minecraft.getInstance().font;
                textRenderer.drawInBatch(text,-textRenderer.width(text) / 2.0f, 0.0F, j, false, matrix4f,arg3, Font.DisplayMode.NORMAL, backgroundColor, l);
                matrices.popPose();
            }
        }

    }


}
