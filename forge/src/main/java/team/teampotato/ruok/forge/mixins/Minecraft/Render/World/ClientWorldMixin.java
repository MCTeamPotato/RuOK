package team.teampotato.ruok.forge.mixins.Minecraft.Render.World;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
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
        if(RuOK.get().RenderWeather  == RuOK.weather.LOW) {
            return super.getRainGradient(delta) * 0.5f; // 减少雨滴数量
        } else if(RuOK.get().RenderWeather == RuOK.weather.CLOSE) {
          return 0;//关闭雨渲染
        }
        return super.getRainGradient(delta);//渲染下雨
    }

    @Override
    public float getThunderGradient(float delta) {
        if(RuOK.get().RenderWeather == RuOK.weather.LOW) {
            return super.getThunderGradient(delta) * 0.5f;  // 减少雪花数量
        } else if(RuOK.get().RenderWeather == RuOK.weather.CLOSE) {
            return 0;//关闭雪渲染
        }
        return super.getThunderGradient(delta);//渲染下雪
    }

}