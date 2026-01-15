//package io.github.jaminajar.jaminajarmod.entity;
//
//import io.github.jaminajar.jaminajarmod.block.entity.SoulBombBlockEntity;
//import net.minecraft.entity.*;
//import net.minecraft.entity.data.DataTracker;
//import net.minecraft.entity.data.TrackedData;
//import net.minecraft.entity.data.TrackedDataHandlerRegistry;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.particle.ParticleTypes;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class SoulerSoulBombEntity extends TntEntity {
//    public int soulEnergy;
//    public SoulerSoulBombEntity(EntityType<? extends TntEntity> entityType, World world) {
//        super(entityType, world);
//        this.intersectionChecked = true;
//    }
//    private static final TrackedData<Integer> FUSE = DataTracker.registerData(SoulerSoulBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private static final int DEFAULT_FUSE = 80;
//    @Nullable
//    private LivingEntity causingEntity;
//
//    public SoulerSoulBombEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter, int soulEnergy) {
//        this(ModEntities.SOUL_BOMB, world);
//        this.setPosition(x, y, z);
//        double d = world.random.nextDouble() * (float) (Math.PI * 2);
//        this.setVelocity(-Math.sin(d) * 0.02, 0.2F, -Math.cos(d) * 0.02);
//        this.setFuse(160+20*soulEnergy);
//        this.prevX = x;
//        this.prevY = y;
//        this.prevZ = z;
//        this.soulEnergy = soulEnergy;
//        this.causingEntity = igniter;
//    }
//
//    @Override
//    protected void initDataTracker() {
//        this.dataTracker.startTracking(FUSE, 80);
//    }
//
//    @Override
//    public void tick() {
//        if (!this.hasNoGravity()) {
//            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
//        }
//
//        this.move(MovementType.SELF, this.getVelocity());
//        this.setVelocity(this.getVelocity().multiply(0.98));
//        if (this.isOnGround()) {
//            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
//        }
//
//        int i = this.getFuse() - 1;
//        this.setFuse(i);
//        if (i <= 0) {
//            this.discard();
//            if (!this.getWorld().isClient) {
//                this.explode();
//            }
//        } else {
//            this.updateWaterState();
//            if (this.getWorld().isClient) {
//                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
//            }
//        }
//    }
//    private void explode() {
//        float f = 4.0F + soulEnergy;
//        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), f, World.ExplosionSourceType.TNT);
//    }
//    @Override
//    protected void writeCustomDataToNbt(NbtCompound nbt) {
//        nbt.putShort("Fuse", (short)this.getFuse());
//    }
//    @Override
//    protected void readCustomDataFromNbt(NbtCompound nbt) {
//        this.setFuse(nbt.getShort("Fuse"));
//    }
//    @Nullable
//    public LivingEntity getOwner() {
//        return this.causingEntity;
//    }
//    public void setFuse(int fuse) {
//        this.dataTracker.set(FUSE, fuse);
//    }
//    public int getFuse() {
//        return this.dataTracker.get(FUSE);
//    }
//}
