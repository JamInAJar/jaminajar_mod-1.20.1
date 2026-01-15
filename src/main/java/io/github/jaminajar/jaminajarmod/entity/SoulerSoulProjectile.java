package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
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
        super.initDataTracker();
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        if (this.age > 200) {
            this.discard();
        }
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
            int souledStrength = 0;
            if (target1 instanceof LivingEntity target) {
                StatusEffectInstance souled = target.getStatusEffect(ModEffects.SOULED);
                if (souled != null) {
                    souledStrength = souled.getAmplifier() + 1;
                }
            }
            if (owner instanceof LivingEntity) {
                DamageSource damageSource = new DamageSource(
                        getWorld().getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(ModDamageTypes.SOULER_SOUL), owner);
                target1.damage(damageSource, 3.0F);

                // Additional hit effects
            }
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(ModEffects.SOULED, 100, 2 + souledStrength);
            takeSoulKnockback(5, target1.getX(), target1.getY(), target1.getZ());
            if (owner instanceof PlayerEntity player) {
                int emptyCount = 0;
                for (ItemStack stack : player.getInventory().main) {
                    if (stack.getItem() == ModItems.EMPTY_SOUL_CANISTER) {
                        emptyCount += stack.getCount();
                    } else if (stack.getItem() == ModItems.EMPTY_SOUL_GRENADE) {
                        emptyCount += stack.getCount();
                    }
                }
                if (target1 instanceof  LivingEntity) {
                    if (emptyCount > 0 && !((LivingEntity) target1).hasStatusEffect(ModEffects.SOULED)) {
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            ItemStack stack = player.getInventory().getStack(i);
                            if (stack.getItem() == ModItems.EMPTY_SOUL_CANISTER) {
                                ItemStack fullStack = new ItemStack(ModItems.FULL_SOUL_CANISTER, 1);
                                player.getInventory().setStack(i, fullStack);
                                break;
                            } else if (stack.getItem() == ModItems.EMPTY_SOUL_GRENADE) {
                                ItemStack fullStack = new ItemStack(ModItems.FULL_SOUL_GRENADE, 1);
                                player.getInventory().setStack(i, fullStack);
                                break;
                            }
                        }
                    } else if(emptyCount<=0){
                        player.sendMessage(Text.literal("Soul Energy Full!"), true);
                    }
                }
                if (target1 instanceof LivingEntity) {
                    ((LivingEntity) target1).addStatusEffect(statusEffectInstance);
                }

            }
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
