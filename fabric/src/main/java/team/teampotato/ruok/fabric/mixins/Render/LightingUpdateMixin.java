package team.teampotato.ruok.fabric.mixins.Render;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import team.teampotato.ruok.fabric.config.RuOK;

@Mixin(LightmapTextureManager.class)
public abstract class LightingUpdateMixin implements PacketListener {
    @Shadow
    private float flickerIntensity;
    @Shadow
    private boolean dirty;

    /**
     * @author AweiMC
     * @reason ...Wdf?
     */
    @Overwrite
    public void tick(){//Stop Tick
        if(!RuOK.get().LightingUpdate){
            this.dirty = false;
        } else {//Start Tick
            this.flickerIntensity += (float)((Math.random() - Math.random()) * Math.random() * Math.random() * 0.1);
            this.flickerIntensity *= 0.9F;
            this.dirty = true;
        }
    }
}