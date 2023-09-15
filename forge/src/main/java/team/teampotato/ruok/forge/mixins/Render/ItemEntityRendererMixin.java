package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin extends EntityRenderer<ItemEntity> {

    @Final
    @Shadow private ItemRenderer itemRenderer;

    @Final
    @Shadow private Random random;

    protected ItemEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Shadow
    protected abstract int getRenderedAmount(ItemStack stack);

    @Inject(method = "render*", at = @At("HEAD"), cancellable = true)
    public void render(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        // 检查是否启用了 FastItemRender
        if (!RuOK.get().FastItemRender) {
            return;
        }

        // 备份当前矩阵栈
        matrixStack.push();

        // 获取物品栈
        ItemStack itemStack = itemEntity.getStack();

        // 随机数种子
        long j = itemStack.isEmpty() ? 187 : Item.getRawId(itemStack.getItem()) + itemStack.getDamage();
        this.random.setSeed(j);

        // 获取物品的模型
        BakedModel bakedModel = this.itemRenderer.getModel(itemStack, itemEntity.getWorld(), null, itemEntity.getId());
        boolean hasDepth = bakedModel.hasDepth();

        // 设置影子半径
        this.shadowRadius = RuOK.get().ItemCastShadows ? 0.15F : 0.0F;

        // 根据物品的年龄和偏移量进行微小的抖动
        float l = MathHelper.sin(((float) itemEntity.getItemAge() + g) / 10.0F + itemEntity.uniqueOffset) * 0.1F + 0.1F;

        // 获取物品模型的比例
        float m = bakedModel.getTransformation().getTransformation(ModelTransformation.Mode.GROUND).scale.getY();

        // 调整渲染位置
        matrixStack.translate(0.0F, l + 0.25F * m, 0.0F);

        // 进行矩阵变换
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));

        // 获取物品模型的缩放
        float o = bakedModel.getTransformation().ground.scale.getX();
        float p = bakedModel.getTransformation().ground.scale.getY();
        float q = bakedModel.getTransformation().ground.scale.getZ();

        // 声明临时变量
        float s;
        float t;

        // 获取物品的堆叠数量
        int renderedAmount = this.getRenderedAmount(itemStack);

        // 如果物品没有深度
        if (!hasDepth) {
            float r = -0.0F * (float)(renderedAmount - 1) * 0.5F * o;
            s = -0.0F * (float)(renderedAmount - 1) * 0.5F * p;
            t = -0.09375F * (float)(renderedAmount - 1) * 0.5F * q;
            matrixStack.translate(r, s, t);
        }

        // 渲染物品堆叠
        for (int u = 0; u < renderedAmount; ++u) {
            matrixStack.push();

            // 如果是堆叠中的物品，进行微小的偏移
            if (u > 0) {
                if (hasDepth) {
                    s = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    t = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    float v = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    matrixStack.translate(s, t, v);
                } else {
                    s = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                    t = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                    matrixStack.translate(s, t, 0.0F);
                }
            }

            // 渲染物品
            this.itemRenderer.renderItem(itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV, bakedModel);
            matrixStack.pop();

            // 如果没有深度，进行微小的偏移
            if (!hasDepth) {
                matrixStack.translate(0.0F * o, 0.0F * p, 0.0425F * q);
            }
        }

        // 恢复矩阵栈
        matrixStack.pop();

        // 调用父类的渲染方法
        super.render(itemEntity, f, g, matrixStack, vertexConsumerProvider, i);

        // 取消原始方法的渲染
        ci.cancel();
    }
}
