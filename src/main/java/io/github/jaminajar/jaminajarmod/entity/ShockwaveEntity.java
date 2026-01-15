package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.enchantment.ModEnchantments;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageSources;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ShockwaveEntity extends ProjectileEntity {

    public BlockState block;
    public int charge;
    public float radius;
    private static final TrackedData<Integer> CHARGE =
            DataTracker.registerData(ShockwaveEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public ShockwaveEntity(EntityType<? extends ProjectileEntity> type, World world) {
        super(type, world);
        this.charge = 0;
        this.radius = 2.0f + (float) Math.sqrt(charge)*0.6f;
    }

    public void setCharge(int charge) {
        this.dataTracker.startTracking(CHARGE,0);
        this.charge = charge;
        this.radius = 2.0f + (float) Math.sqrt(charge) * 0.2f;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        shockwaveDamage();

    }

    @Override
    public void tick() {
        super.tick();
        shockwaveDamage();
    }
    public void shockwaveDamage(){
        World world = this.getWorld();
        if(world==null||world.isClient) return;
        if(this.getOwner() instanceof PlayerEntity player) {
            charge = charge + 20*Math.max(EnchantmentHelper.getLevel(ModEnchantments.BULLDOZE,player.getMainHandStack()),EnchantmentHelper.getLevel(ModEnchantments.BULLDOZE,player.getOffHandStack()));
            /// max charge effectively 300
        }
        DamageSource source = new ModDamageSources(this.getWorld().getRegistryManager()).flattened(this.getWorld(),this.getOwner());
        List<LivingEntity> targets = this.getWorld().getEntitiesByClass(
                LivingEntity.class,
                this.getBoundingBox().expand(radius),
                e -> e != null && e.isAlive()
        );

        for (LivingEntity target : targets) {
            if(target!=this.getOwner()) {
                target.damage(source, (float) ((charge - this.getPos().squaredDistanceTo(target.getPos())) / 10));
                /// max damage effectively 25
                target.takeKnockback(MathHelper.clamp((charge - this.getPos().squaredDistanceTo(target.getPos())) / 25, 0.5d, 4d+ (double) charge /80), this.getX() - target.getX(), this.getZ() - target.getZ());
                /// max knockback effectively 6
            }

        }
        float particleOffsetAmount = 10f + MathHelper.ceil((float) charge / 20);
        if (world instanceof ServerWorld serverWorld) {
            int points = (int)(32 * Math.max(particleOffsetAmount, 1));
            double speed = 0.4 + particleOffsetAmount * 0.45;

            serverWorld.spawnParticles(
                    ParticleTypes.EXPLOSION,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    1,
                    0.2, 0.2, 0.2,
                    0.05
            );

            for (int i = 0; i < points; i++) {
                double angle = i * (Math.PI * 2 / points);
                double px = this.getX();
                double py = this.getY() + 0.5;
                double pz = this.getZ();

                serverWorld.spawnParticles(
                        ParticleTypes.POOF,
                        px, py, pz,
                        0,
                        Math.cos(angle) * speed,
                        0.02,
                        Math.sin(angle) * speed,
                        0.1
                );
            }
        }
        this.discard();
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
        this.setVelocity(0,0,0);
    }

    @Override
    protected void initDataTracker() {

    }
}