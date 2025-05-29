package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.enchantment.ModEnchantments;
import io.github.jaminajar.jaminajarmod.items.ModToolMaterials;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Objects;


public class MarshmallowStickItem extends ToolItem {
    private final float attackDamage;
    private final int yesCooked;
    private final int yesNetherite;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public MarshmallowStickItem(ModToolMaterials toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings, int yesCooked, int yesNetherite) {
        super(toolMaterial, settings);
        this.attackDamage = (float) attackDamage + toolMaterial.getAttackDamage();
        this.yesCooked = yesCooked;
        this.yesNetherite = yesNetherite;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID,
                "Weapon modifier", attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
                "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                user.getEatSound(stack),
                SoundCategory.NEUTRAL,
                1.0F,
                1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
        );
        this.applyFoodEffects(stack, world, user);
        int amount = 250 * (3 + yesCooked - yesNetherite)/(EnchantmentHelper.getLevel(Enchantments.UNBREAKING,stack)/2+1);
        if (!(user instanceof PlayerEntity) || !((PlayerEntity) user).getAbilities().creativeMode) {
            if (amount >= stack.getMaxDamage()-stack.getDamage()) {
                if(yesNetherite==0){
                    return new ItemStack(Items.WOODEN_SWORD);
                } else if(yesNetherite==1){
                    return new ItemStack(Items.NETHERITE_SWORD);
                }

            }
            stack.damage(amount,
                    user,
                    e -> e.sendEquipmentBreakStatus(
                            user.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));
        }

        return stack;
    }

    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity) {
        Item item = stack.getItem();
        if (targetEntity instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (item.isFood()) {
            for (Pair<StatusEffectInstance, Float> pair : Objects.requireNonNull(item.getFoodComponent()).getStatusEffects()) {
                if (!world.isClient && pair.getFirst() != null && world.random.nextFloat() < pair.getSecond()) {
                    targetEntity.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
                }
            }
            if (targetEntity instanceof PlayerEntity player) {
                var food = item.getFoodComponent();
                player.getHungerManager().add(food.getHunger(), food.getSaturationModifier());
            }
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.postHit(stack, target, attacker);
        if(attacker instanceof PlayerEntity player){
            if (player.getAttackCooldownProgressPerTick() >= 0.999f) {
                StatusEffectInstance gooedEffect;
                int netherited = yesNetherite;
                int cooked = yesCooked;
                int level = EnchantmentHelper.getLevel(ModEnchantments.GOOEYNESS, stack);
                gooedEffect = new StatusEffectInstance(
                        ModEffects.GOOED,
                        level*(10+2*netherited)+100*cooked,
                        cooked*EnchantmentHelper.getLevel(ModEnchantments.GOOEYNESS, stack)
                );
                target.setStatusEffect(gooedEffect, attacker);
            }
        }
        stack.damage(3,
                attacker,
                e -> e.sendEquipmentBreakStatus(
                        attacker.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));

        return result;
    }
}
