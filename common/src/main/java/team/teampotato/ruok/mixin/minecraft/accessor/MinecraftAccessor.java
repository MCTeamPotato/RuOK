package team.teampotato.ruok.mixin.minecraft.accessor;


import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
    @Accessor("fps")
    static int getFPS() {
        return 0;
    }
}
