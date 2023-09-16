package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(SignBlockEntityRenderer.class)
public abstract class SignBlockEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/block/entity/SignBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At("HEAD"),
            cancellable = true)
    public void onRender(SignBlockEntity arg, float f, MatrixStack arg2, VertexConsumerProvider arg3, int i, int j, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        World world = mc.world;
        Box box = null;
        if (mc.player != null) box = mc.player.getBoundingBox().expand(RuOK.get().Render_Entities_Distance);
        if (world == null) {
            if (box == null) return;
            Vec3d Pos = mc.player.getPos();
            if (!box.contains(Pos)) {
                ci.cancel();
            }
        }

    }
}

