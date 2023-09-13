package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(value = MangroveRootsBlock.class, priority = 1900)
public abstract class MangroveRoots extends Block {
    public MangroveRoots(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState neighborState, Direction offset) {
        if (RuOK.get().RenderMangroveRoots) {
            return neighborState.getBlock() instanceof MangroveRootsBlock;
        }
        else return false;
    }
}