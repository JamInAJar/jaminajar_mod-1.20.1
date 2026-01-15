package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;


public class SoulerBeamProjectile extends PersistentProjectileEntity {
    public SoulerBeamProjectile(EntityType<? extends SoulerBeamProjectile> entityType, World world) {
        super(entityType, world);
    }

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    private int consumedCanisters = 0;

    public void setConsumedCanisters(int count) {
        this.consumedCanisters = Math.min(3, count);
    }


    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
//        if (!this.getWorld().isClient) {
//        }
        if (this.age > 200) {
            this.discard();
        }
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(
                    ParticleTypes.SOUL,
                    this.getX(), this.getY(), this.getZ(),
                    0.0, 0.0, 0.0
            );
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    private ItemStack stack = ItemStack.EMPTY;

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity owner = this.getOwner();
        Entity target = entityHitResult.getEntity();

        if (owner != null) {
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_LASER), owner);

            target.damage(damageSource, 7.5f * consumedCanisters);

            if (target instanceof LivingEntity living) {
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 40, 7));
            }
        }
        this.discard();
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
