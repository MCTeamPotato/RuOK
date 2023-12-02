package team.teampotato.ruok.forge.mixins.Minecraft.Invoker;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WorldRenderer.class)
public interface WorldRendererInvoker {
    @Invoker("reload")
    void invokeReload();
}

