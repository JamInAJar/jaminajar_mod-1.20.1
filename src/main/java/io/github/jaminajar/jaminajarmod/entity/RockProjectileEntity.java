package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.enchantment.ModEnchantments;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageSources;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.util.Utils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

/// THIS ROCK HAS TAKEN HOURS AWAY FROM MY LIFE THAT I WILL NEVER GET BACK
public class RockProjectileEntity extends ThrownItemEntity {
    public double powerX;
    public double powerY;
    public double powerZ;

    protected float getDrag() {
        return 0.95F;
    }

    public RockProjectileEntity(EntityType<? extends RockProjectileEntity> type, World world) {
        super(type, world);
        this.setNoGravity(false);
        this.noClip = false;

    }

    public RockProjectileEntity(World world) {
        super(ModEntities.ROCK_PROJECTILE, world);
        this.noClip = false;
    }

    @Override
    public void tick() {
        if (!this.isAlive()) return;

        Vec3d start = this.getPos();

        Vec3d velocity = this.getVelocity()
                .add(0, -this.getGravity(), 0)
                .add(this.powerX, this.powerY, this.powerZ)
                .multiply(this.getDrag());

        this.setVelocity(velocity);

        Vec3d end = start.add(velocity);

        this.handleHighSpeedCollision(start, end);

        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();

        this.setPosition(end);
        this.refreshPosition();
    }


    private void handleHighSpeedCollision(Vec3d start, Vec3d end) {
        World world = this.getWorld();

        int steps = MathHelper.ceil(start.distanceTo(end) / 0.5);
        Vec3d step = end.subtract(start).multiply(1.0 / steps);

        Vec3d current = start;

        for (int i = 0; i < steps; i++) {
            Vec3d next = current.add(step);

            BlockHitResult blockHit = world.raycast(new RaycastContext(
                    current,
                    next,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    this
            ));
            if (blockHit.getType() != HitResult.Type.MISS) {
                this.onBlockHit(blockHit);
                return;
            }
            EntityHitResult entityHit = ProjectileUtil.getEntityCollision(
                    world,
                    this,
                    current,
                    next,
                    this.getBoundingBox().stretch(step).expand(0.3),
                    this::canHit
            );

            if (entityHit != null) {
                this.onEntityHit(entityHit);
                return;
            }

            current = next;
        }
    }
    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.scheduleVelocityUpdate();
            Entity entity = source.getAttacker();
            if (!(entity instanceof PlayerEntity player)) return false;
            ItemStack stack = player.getMainHandStack();
            if (!(stack.isOf(ModItems.GIGATON_HAMMER))) return false;


            if (!this.getWorld().isClient) {
                Vec3d vec3d = entity.getRotationVector();
                boolean crit = Utils.CombatUtils.isCriticalHit((PlayerEntity) this.getOwner());
                Vec3d velocity = vec3d.multiply((3 + (double) EnchantmentHelper.getLevel(ModEnchantments.ROCK_SMASH, stack) / 1.5) * (1 + 0.1 * (Utils.OtherUtils.convertBooleanToBinary(crit))));
                this.setVelocity(velocity);
                this.velocityDirty = true;
                this.powerX = vec3d.x * 0.1;
                this.powerY = vec3d.y * 0.1;
                this.powerZ = vec3d.z * 0.1;
                this.setOwner(entity);
            }

            return true;
        }
    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        return new EntitySpawnS2CPacket(
                this.getId(),
                this.getUuid(),
                this.getX(),
                this.getY(),
                this.getZ(),
                this.getPitch(),
                this.getYaw(),
                this.getType(),
                i,
                new Vec3d(this.powerX, this.powerY, this.powerZ),
                0.0
        );
    }

    @Override
    protected boolean canHit(Entity entity) {
        return entity != this.getOwner() && super.canHit(entity);
    }


    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double d = packet.getVelocityX();
        double e = packet.getVelocityY();
        double f = packet.getVelocityZ();
        double g = Math.sqrt(d * d + e * e + f * f);
        if (g != 0.0) {
            this.powerX = d / g * 0.1;
            this.powerY = e / g * 0.1;
            this.powerZ = f / g * 0.1;
        }
    }

    @Override
    protected float getGravity() {
        return 0.06f;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        World world = this.getWorld();
        if (world == null || world.isClient) return;
        DamageSource source = new ModDamageSources(this.getWorld().getRegistryManager()).flattened(this.getWorld(), this.getOwner());

        float amount = (float) Math.max((this.getVelocity().length()) * 5 + 3, 3);
        float particleOffsetAmount = amount/20;
        if (world instanceof ServerWorld serverWorld) {
            int points = (int) (24*Math.max(Math.floor(2*particleOffsetAmount)/2,1));
            double speed = 0.35* particleOffsetAmount;

            serverWorld.spawnParticles(
                    ParticleTypes.EXPLOSION,
                    this.getX(), this.getY(), this.getZ(),
                    1,
                    0, 0, 0,
                    0
            );

            for (int i = 0; i < points; i++) {
                double angle = i * (Math.PI * 2 / points);

                serverWorld.spawnParticles(
                        ParticleTypes.POOF,
                        this.getX(),
                        this.getY() + 0.05,
                        this.getZ(),
                        0,
                        Math.cos(angle) * speed,
                        particleOffsetAmount/20,
                        Math.sin(angle) * speed,
                        particleOffsetAmount
                );
            }
        }
        world.getOtherEntities(this, this.getBoundingBox().expand(Math.max(0.5f, MathHelper.clamp(amount / 8, 0.5f, 2f))))
                .forEach(entity -> {
                    if (entity instanceof LivingEntity living) {
                        Utils.CombatUtils.damageIgnoringIFrames(living, source, (float) (Math.max(amount - this.getPos().distanceTo(living.getPos()), 0)));
                        living.takeKnockback(MathHelper.clamp((amount - this.getPos().distanceTo(living.getPos())) / 25, 0.5d, 4d), this.getX() - living.getX(), this.getZ() - living.getZ());
                    } else {
                        entity.damage(source, (float) (Math.max(amount - this.getPos().distanceTo(entity.getPos()), 0)));
                    }
                });

        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();
        DamageSource source = new ModDamageSources(this.getWorld().getRegistryManager()).flattened(this.getWorld(), this.getOwner());

        float amount = (float) Math.max((this.getVelocity().length()) * 5 + 3, 3);
        float particleOffsetAmount = amount/20;
        if (world instanceof ServerWorld serverWorld) {
            int points = (int) (24*Math.max(Math.floor(2*particleOffsetAmount)/2,1));
            double speed = 0.35* particleOffsetAmount;

            serverWorld.spawnParticles(
                    ParticleTypes.EXPLOSION,
                    this.getX(), this.getY(), this.getZ(),
                    1,
                    0, 0, 0,
                    0
            );

            for (int i = 0; i < points; i++) {
                double angle = i * (Math.PI * 2 / points);

                serverWorld.spawnParticles(
                        ParticleTypes.POOF,
                        this.getX(),
                        this.getY() + 0.05,
                        this.getZ(),
                        0,
                        Math.cos(angle) * speed,
                        particleOffsetAmount/20,
                        Math.sin(angle) * speed,
                        particleOffsetAmount
                );
            }
        }
        world.getOtherEntities(this, this.getBoundingBox().expand(Math.max(1.5f, MathHelper.clamp(amount / 8, 0.5f, 2f))))
                .forEach(entity -> {
                    if (entity instanceof LivingEntity living) {
                        Utils.CombatUtils.damageIgnoringIFrames(living, source, (float) (Math.max(amount - this.getPos().distanceTo(living.getPos()), 0)));
                        living.takeKnockback(MathHelper.clamp((amount - this.getPos().distanceTo(living.getPos())) / 25, 0.5d, 4d), this.getX() - living.getX(), this.getZ() - living.getZ());
                    } else {
                        entity.damage(source, (float) (Math.max(amount - this.getPos().distanceTo(entity.getPos()), 0)));
                    }

                });
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ROCK;
    }

}
