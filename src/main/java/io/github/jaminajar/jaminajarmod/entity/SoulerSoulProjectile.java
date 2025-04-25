package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.effects.SouledEffect;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SoulerSoulProjectile extends PersistentProjectileEntity {

    public SoulerSoulProjectile(EntityType<? extends SoulerSoulProjectile> entityType, World world) {
        super(entityType, world);
    }

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }


    @Override
    protected void initDataTracker() {
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    public void takeSoulKnockback(double strength, double x, double y, double z) {
        this.velocityDirty = true;
        Vec3d vec3d = this.getVelocity();
        Vec3d vec3d2 = new Vec3d(x, y, z).normalize().multiply(strength);
        this.setVelocity(-vec3d.x / 2.0 - vec3d2.x, this.isOnGround() ? Math.min(0.4, vec3d.y / 2.0 + strength) : vec3d.y, -vec3d.z / 2.0 - vec3d2.z);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient()) {
            Entity target1 = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            SouledEffect souledEffect = new SouledEffect(StatusEffectCategory.HARMFUL, 2551850);

            int souledStrength = 0;
            if (target1 instanceof LivingEntity target) {
                StatusEffectInstance souled = target.getStatusEffect(ModEffects.SOULED);
                if (souled != null) {
                    souledStrength = souled.getAmplifier() + 1;
                }
            }
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(souledEffect, 30, 7 + souledStrength);
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_SOULED_DAMAGE));
            target1.damage(damageSource, 5.0F);
            takeSoulKnockback(5, target1.getX(), target1.getY(), target1.getZ());

            if (owner instanceof LivingEntity shooter) {
                ItemStack heldItem = shooter.getMainHandStack();
                assert target1 instanceof LivingEntity;
                ((LivingEntity) target1).addStatusEffect(statusEffectInstance);
                if (heldItem.hasNbt()) {
                    NbtCompound tag = heldItem.getNbt();
                    assert tag != null;
                    int soulEnergy = tag.getInt("SoulEnergy");
                    if (soulEnergy == 3) {
                        owner.sendMessage(Text.literal("No Soul Energy!"));
                    } else {
                        tag.putInt("SoulEnergy", soulEnergy + 1);
                    }
                } else {
                    NbtCompound tag = new NbtCompound();
                    tag.putInt("SoulEnergy", 1);
                    heldItem.setNbt(tag);
                }
            }
            this.discard();
        }
    }
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult){
        this.discard();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
