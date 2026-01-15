package io.github.jaminajar.jaminajarmod.entity.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public interface ModDamageTypes {
    RegistryKey<DamageType> SOULER_SOUL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "souled"));
    RegistryKey<DamageType> SOULER_LASER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "lasered"));
    RegistryKey<DamageType> SOULER_SELFSOUL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "selfsouled"));
    RegistryKey<DamageType> UNATTRIBUTED_NOTE_PROJECTILE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("unattributed_note_projectile"));
    RegistryKey<DamageType> NOTE_PROJECTILE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod","note_projectile_damage"));
    RegistryKey<DamageType> HELICOPTER_SWORD_SPIN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod","heli_sword_spin"));
    RegistryKey<DamageType> CACOPHONY_OVERBLOW= RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "cacophony_overblow"));
    RegistryKey<DamageType> BAMBOO_PROJECTILE= RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "falling_bamboo"));
    RegistryKey<DamageType> DRIPSTONE_PROJECTILE= RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "falling_dripstone"));
    RegistryKey<DamageType> SEARING_FIRE= RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "searing_fire"));
    RegistryKey<DamageType> FLATTENED= RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("jaminajarmod", "flattened"));
   // static void bootstrap(Registerable<DamageType> damageTypeRegisterable){
    //}
   public static DamageSource of(World world, RegistryKey<DamageType> key) {
       return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
   }
}



