package team.teampotato.ruok.forge.mixins.Render;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import team.teampotato.ruok.forge.config.RuOK;

@Mixin(LightmapTextureManager.class)
public abstract class LightingUpdateMixin implements PacketListener {

    @Shadow
    private float field_21528;
    @Shadow
    private boolean dirty;

    /**
     * @author AweiMC
     * @reason ...Wdf?
     */
    @Overwrite
    public void tick(){//Stop Tick
        if(!RuOK.get().LightingUpdate) {
            this.dirty = false;
        } else{
            this.field_21528 = (float)((double)this.field_21528 + (Math.random() - Math.random()) * Math.random() * Math.random() * 0.1);
            this.field_21528 = (float)((double)this.field_21528 * 0.9);
            this.dirty = true;
        }
    }
}