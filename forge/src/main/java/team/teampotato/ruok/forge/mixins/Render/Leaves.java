package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(value = LeavesBlock.class, priority = 1900)
public abstract class Leaves extends Block {
    public Leaves(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState neighborState, Direction offset) {
        if (RuOK.get().RenderLeaves) {
            return neighborState.getBlock() instanceof LeavesBlock;
        }
        else return false;
    }
}