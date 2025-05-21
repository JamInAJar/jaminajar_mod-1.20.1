package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public class ModEntities {
    public static final EntityType<NoteProjectileEntity> NOTE_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(JamInAJarMod.MOD_ID, "note_projectile"),
            FabricEntityTypeBuilder.<NoteProjectileEntity>create(SpawnGroup.MISC, NoteProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(64)
                    .trackedUpdateRate(10)
                    .build()
    );
    public static final EntityType<HonkProjectileEntity> HONK_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JamInAJarMod.MOD_ID,"honk_projectile"),
            FabricEntityTypeBuilder.<HonkProjectileEntity>create(SpawnGroup.MISC,HonkProjectileEntity::new)
                    .dimensions(EntityDimensions
                            .fixed(3.5f,3.5f))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(10)
                    .build());
    public static final EntityType<SoulerSoulProjectile> SOULER_SOUL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JamInAJarMod.MOD_ID,"souler_soul_projectile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC,SoulerSoulProjectile::new)
                    .dimensions(EntityDimensions
                            .fixed(9f,2.5f))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(10)
                    .build());
    public static final EntityType<SoulerBeamProjectile> SOULER_BEAM_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JamInAJarMod.MOD_ID,"souler_beam_projectile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC,SoulerBeamProjectile::new)
                    .dimensions(EntityDimensions
                            .fixed(0.01f,0.01f))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(10)
                    .build());
    public static final EntityType<HelicopterSwordEntity> HELISWORD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(JamInAJarMod.MOD_ID,"helisword_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, HelicopterSwordEntity::new)
                    .dimensions(EntityDimensions
                            .fixed(5f,5f))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(10)
                    .build());
    private World world;


    public World getWorld() {
        return this.world;
    }
    protected void setWorld(World world) {
        this.world = world;
    }
}
