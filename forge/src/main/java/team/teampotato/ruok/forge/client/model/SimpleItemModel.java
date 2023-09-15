package team.teampotato.ruok.forge.client.model;



import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SimpleItemModel implements BakedModel {

    private BakedModel flattenedItem;
    private final List<BakedQuad> nullQuadList = new ObjectArrayList<>();

    public void setItem(BakedModel model) {
        this.flattenedItem = model;
    }

    private boolean isCorrectDirectionForType(Direction direction) {
        return direction == Direction.SOUTH;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        if(face != null) {
            return isCorrectDirectionForType(face) ? flattenedItem.getQuads(state, face, random) : ImmutableList.of();
        } else {
            nullQuadList.clear();
            List<BakedQuad> realList = flattenedItem.getQuads(state, null, random);
            for (BakedQuad quad : realList) {
                if (isCorrectDirectionForType(quad.getFace())) {
                    nullQuadList.add(quad);
                }
            }
            return nullQuadList;
        }
    }

    @Override
    public boolean useAmbientOcclusion() {
        return flattenedItem.useAmbientOcclusion();
    }

    @Override
    public boolean hasDepth() {
        return flattenedItem.hasDepth();
    }

    @Override
    public boolean isSideLit() {
        return flattenedItem.isSideLit();
    }

    @Override
    public boolean isBuiltin() {
        return flattenedItem.isBuiltin();
    }

    @Override
    public Sprite getParticleSprite() {
        return flattenedItem.getParticleSprite();
    }

    @Override
    public ModelTransformation getTransformation() {
        return flattenedItem.getTransformation();
    }

    @Override
    public ModelOverrideList getOverrides() {
        return flattenedItem.getOverrides();
    }
}