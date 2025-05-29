package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.jaminajar.jaminajarmod.enchantment.ModEnchantments;
import io.github.jaminajar.jaminajarmod.items.ModToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class BoomtubeItem extends AmmoItem {
    private final int maxAmmo;
    public int pentaboomEnable=0;
    public int explosionPower = 2;

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public BoomtubeItem(ModToolMaterials toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings, int maxAmmo) {
        super(settings, Items.GUNPOWDER, 32);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID,
                "Weapon modifier", attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
                "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        this.maxAmmo = maxAmmo;
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    public static int getPentaboomCounter(ItemStack stack) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().contains("PentaboomCounter")) {
                return stack.getNbt().getInt("PentaboomCounter");
            }
        }
        return 0;
    }

    public static void setPentaboomCounter(ItemStack stack, int value) {
        stack.getOrCreateNbt().putInt("PentaboomCounter", value);
    }


    public int getMaxAmmo(){
        return maxAmmo;
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean superHit = super.postHit(stack, target, attacker);

        stack.damage(16, attacker, e -> e.sendEquipmentBreakStatus(
                attacker.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));

        int blastEnchant = EnchantmentHelper.getLevel(ModEnchantments.BLAST, stack);
        int hasPentaboom = EnchantmentHelper.getLevel(ModEnchantments.PENTABOOM, stack);

        if (hasPentaboom > 0) {
            int pentaboomCount = (getPentaboomCounter(stack) + 1) % 5;
            setPentaboomCounter(stack, pentaboomCount);

            if (pentaboomCount == 0) {
                attacker.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 1.0F, 1.0F);
                pentaboomEnable = 5;
            } else {
                pentaboomEnable = 0;
            }
        } else {
            pentaboomEnable = 0;
        }

        explosionPower = blastEnchant + 2 + pentaboomEnable;

        if (getAmmo(stack) <= maxAmmo && getAmmo(stack) != 0) {
            target.getWorld().createExplosion(null,
                    target.getX(), target.getY(), target.getZ(),
                    explosionPower, false, World.ExplosionSourceType.MOB);
            setAmmo(stack, getAmmo(stack) - 1);
            return superHit;
        }
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.jaminajarmod.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
