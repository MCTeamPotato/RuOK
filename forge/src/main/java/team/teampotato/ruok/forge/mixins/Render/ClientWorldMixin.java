package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {

    public ClientWorldMixin(
            MutableWorldProperties properties,
            RegistryKey<World> registryRef,
            RegistryEntry<DimensionType> dimensionTypeEntry,
            Supplier<Profiler> profiler,
            boolean isClient,
            boolean debugWorld,
            long seed,
            int maxChainedNeighborUpdates
    ) {
        super(properties, registryRef, dimensionTypeEntry, profiler, isClient, debugWorld,seed,maxChainedNeighborUpdates);
    }

    @Override
    public float getRainGradient(float delta) {
        return RuOK.get().RainRender ? 0 : super.getRainGradient(delta); // 关闭雨渲染
    }

    @Override
    public float getThunderGradient(float delta) {
        return RuOK.get().ThunderRender ? 0 : super.getThunderGradient(delta);  // 关闭雷渲染
    }

}