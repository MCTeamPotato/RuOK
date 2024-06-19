package team.teampotato.ruok.forge.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import team.teampotato.ruok.forge.config.RuOK;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {


    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    @Override
    public float getRainGradient(float delta) {
        switch (RuOK.get().RenderWeather) {
            case LOW:
                return super.getRainGradient(delta) / 2;
            case CLOSE:
                return 0;
            default:
                return super.getRainGradient(delta);
        }
    }

    @Override
    public float getThunderGradient(float delta) {
        switch (RuOK.get().RenderWeather) {
            case LOW:
                return super.getThunderGradient(delta) / 2;
            case CLOSE:
                return 0;
            default:
                return super.getThunderGradient(delta);
        }
    }


}