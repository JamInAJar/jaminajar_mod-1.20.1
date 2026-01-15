package io.github.jaminajar.jaminajarmod.entity.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModDamageSources {

    public final Registry<DamageType> registry;


    public ModDamageSources(DynamicRegistryManager registryManager){
        this.registry = registryManager.get(RegistryKeys.DAMAGE_TYPE);
    }

    public DamageSource flattened(World world, Entity attacker) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.FLATTENED),attacker);
    }

    public final DamageSource create(RegistryKey<DamageType> key) {
        return new DamageSource(this.registry.entryOf(key));
    }

    public final DamageSource create(RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(this.registry.entryOf(key), attacker);
    }

    public final DamageSource create(RegistryKey<DamageType> key, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(this.registry.entryOf(key), source, attacker);
    }
}
