package io.github.jaminajar.jaminajarmod.entity.damage;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends TagProvider<DamageType> {
    public ModDamageTypeTagProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> maxChainedNeighborUpdates) {
        super(output, RegistryKeys.DAMAGE_TYPE, maxChainedNeighborUpdates);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_INVULNERABILITY).add(ModDamageTypes.HELICOPTER_SWORD_SPIN).add(ModDamageTypes.NOTE_PROJECTILE).add(ModDamageTypes.UNATTRIBUTED_NOTE_PROJECTILE);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageTypes.SOULER_LASERED_DAMAGE);
    }
}
