package io.github.jaminajar.jaminajarmod.entity.damage;

import io.github.jaminajar.jaminajarmod.entity.SoulerSoulProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.Nullable;

public class ModDamageSources {

    public final Registry<DamageType> registry;
    private final DamageSource soulerSoul;

    public ModDamageSources(DynamicRegistryManager registryManager, DamageSource soulerSoul){
        this.registry = registryManager.get(RegistryKeys.DAMAGE_TYPE);

        this.soulerSoul = soulerSoul;
    }
    public ModDamageSources(Registry<DamageType> registry) {
        this.registry = registry;
        this.soulerSoul = create(ModDamageTypes.SOULER_SOUL);
    }
    public DamageSource soulerSoul() {
        return this.soulerSoul;
    }

    public DamageSource soulerSoulProjectile(SoulerSoulProjectile source, @Nullable Entity attacker) {
        return this.create(ModDamageTypes.SOULER_SOUL, source, attacker);
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
