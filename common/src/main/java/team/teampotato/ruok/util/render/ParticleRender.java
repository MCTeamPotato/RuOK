package team.teampotato.ruok.util.render;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TerrainParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.teampotato.ruok.config.RuOK;
import team.teampotato.ruok.gui.vanilla.mode.BlockBreakParticleType;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParticleRender {
    private static final HashSet<ParticleType<?>> blackParticleListCache = new HashSet<>();
    private static final HashSet<ParticleType<?>> whiteParticleListCache = new HashSet<>();
    private static final Minecraft mc = Minecraft.getInstance();

    static {
        initConfigList();
    }
    // 初始化生物列表
    private static void initConfigList() {
        List<String> blackParticleConfig = RuOK.get().BlackListedParticle;
        List<String> whiteParticleConfig = RuOK.get().WhiteListedParticle;
        for (String bc : blackParticleConfig) {
            Optional<ParticleType<?>> entityTypeOpt = particleTypeGet(bc);
            entityTypeOpt.ifPresent(blackParticleListCache::add);
        }
        for (String wc : whiteParticleConfig) {
            Optional<ParticleType<?>> entityTypeOpt = particleTypeGet(wc);
            entityTypeOpt.ifPresent(whiteParticleListCache::add);
        }
    }

    public static Optional<ParticleType<?>> particleTypeGet(String id) {
        return BuiltInRegistries.PARTICLE_TYPE.getOptional(ResourceLocation.tryParse(id));
    }

    // 重新加载生物列表
    public static void reloadList() {
        // 清空当前的缓存
        blackParticleListCache.clear();
        whiteParticleListCache.clear();
        // 重新加载生物列表
        initConfigList();

    }
    public static boolean isParticleWhitelisted(@NotNull ParticleOptions particle) {
        return whiteParticleListCache.contains(particle.getType()); // 返回 true 表示在白名单中 - 添加渲染
    }
    public static boolean isParticleBlacklisted(@NotNull ParticleOptions particle) {
        return blackParticleListCache.contains(particle.getType()); // 返回 true 表示在白名单中 - 添加渲染
    }
    public static boolean isParticleBlacklisted(@NotNull ParticleType<?> particle) {
        return blackParticleListCache.contains(particle); // 返回 true 表示在白名单中 - 添加渲染
    }
    public static void addBlockBreakParticles(BlockPos pos, @NotNull BlockState state) {
        BlockBreakParticleType currentQuality = RuOK.get().BlockBreakParticleMode;
        // 检查方块是否需要生成破碎粒子
        if (!state.isAir() && state.shouldSpawnTerrainParticles()) {
            Vec3 playerPos = Objects.requireNonNull(mc.player).position();
            double maxDistance = RuOK.get().MaxParticleDistance;
            double distance = playerPos.distanceToSqr(new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

            // 如果玩家距离方块太远，不生成粒子
            if (distance > maxDistance * maxDistance) {
                return;
            }
            if(!RuOK.get().Particle) {
                return;
            }
            if(isParticleBlacklisted(ParticleTypes.BLOCK)) {
                return;
            }
            // 计算方块与玩家的距离
            VoxelShape voxelShape = state.getShape(mc.level, pos);

            // 粒子生成逻辑
            voxelShape.forAllEdges((minX, minY, minZ, maxX, maxY, maxZ) -> {
                double d = Math.min(1.0, maxX - minX);
                double e = Math.min(1.0, maxY - minY);
                double f = Math.min(1.0, maxZ - minZ);

                // 计算基础粒子数量
                int i = Math.max(2, Mth.ceil(d / 0.25));
                int j = Math.max(2, Mth.ceil(e / 0.25));
                int k = Math.max(2, Mth.ceil(f / 0.25));

                // 根据画质等级调整粒子数量
                i = Math.min(i, currentQuality.getX());
                j = Math.min(j, currentQuality.getY());
                k = Math.min(k, currentQuality.getZ());

                // 生成粒子
                for (int l = 0; l < i; ++l) {
                    for (int m = 0; m < j; ++m) {
                        for (int n = 0; n < k; ++n) {
                            double g = ((double) l + 0.5) / (double) i;
                            double h = ((double) m + 0.5) / (double) j;
                            double o = ((double) n + 0.5) / (double) k;
                            double p = g * d + minX;
                            double q = h * e + minY;
                            double r = o * f + minZ;

                            // 检查粒子是否在方块内
                            Vec3 particlePos = new Vec3(pos.getX() + p, pos.getY() + q, pos.getZ() + r);
                            // 添加粒子到世界中
                            mc.particleEngine.add(new TerrainParticle(
                                    mc.level,
                                    particlePos.x,
                                    particlePos.y,
                                    particlePos.z,
                                    g - 0.5,
                                    h - 0.5,
                                    o - 0.5,
                                    state,
                                    pos
                            ));
                        }
                    }
                }
            });
        }
    }
    public static void onParticleCull(ParticleOptions particleEffect, double x, double y, double z, CallbackInfo ci) {
        Camera camera = mc.gameRenderer.getMainCamera();
        if(ParticleRender.isParticleWhitelisted(particleEffect)) {
            return;
        }
        if(ParticleRender.isParticleBlacklisted(particleEffect)) {
            ci.cancel();
        }
        if(camera.getPosition().distanceToSqr(x, y, z) > RuOK.get().MaxParticleDistance) {
            ci.cancel();
        }
    }
//    public static Particle onParticleCull(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
//        // 获取玩家位置
//        Minecraft mc = Minecraft.getInstance();
//        Camera camera = mc.gameRenderer.getCamera();
//        Particle create = mc.particleManager.createParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
//        // 白名单粒子不做处理 一直渲染,超出范围也渲染
//        if (ParticleRender.isParticleWhitelisted(parameters)) {
//            // 直接返回 null 表示不做任何剔除，粒子应该始终渲染
//            return create;
//        }
//
//        // 黑名单粒子直接取消
//        if (ParticleRender.isParticleBlacklisted(parameters)) {
//            return null;
//        }
//
//        // 检查是否启用了粒子功能
//        if (!RuOK.get().Particle) {
//            return null;
//        }
//
//        // 超过最大距离不生成粒子
//        if (camera.getPos().squaredDistanceTo(x, y, z) > RuOK.get().MaxParticleDistance) {
//            return null;
//        }
//
//        // 创建粒子
//        return create;
//    }


    public static void onParticleCull(ParticleOptions parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<Particle> cir) {
        // 获取玩家位置
        Minecraft mc = Minecraft.getInstance();
        Camera camera = mc.gameRenderer.getMainCamera();
        // 白名单粒子不做处理 一直渲染,超出范围也渲染
        if (ParticleRender.isParticleWhitelisted(parameters)) {
            // 直接返回 null 表示不做任何剔除，粒子应该始终渲染
            return;
        }

        //黑名单粒子直接取消
        if (ParticleRender.isParticleBlacklisted(parameters)) {
            cir.setReturnValue(null);
        }


        // 超过最大距离不生成粒子
        if ((camera.getPosition().distanceToSqr(x, y, z) > RuOK.get().MaxParticleDistance)) {
            cir.setReturnValue(null);
        }

    }
}
