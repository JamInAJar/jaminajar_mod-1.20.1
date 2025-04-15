package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import io.github.jaminajar.jaminajarmod.effects.GooedEffect;
import io.github.jaminajar.jaminajarmod.enchantment.GooeynessEnchantment;
import io.github.jaminajar.jaminajarmod.items.ModToolMaterials;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        if (!(user instanceof PlayerEntity) || !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.damage(250*(3+yesCooked-yesNetherite),
                    user,
                    e -> e.sendEquipmentBreakStatus(
                            user.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));
        }
        return stack;
    }

    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity) {
        Item item = stack.getItem();
        super.finishUsing(stack, world, targetEntity);
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
        }
    }
    ///public void setCooked(ItemStack stack,int cookedness){
    ///    stack.getOrCreateNbt().putInt("Cookedness", MathHelper.clamp(cookedness,0,yesCooked));
    ///}
    ///public void setNetherite(ItemStack stack,int netherited){
    ///    stack.getOrCreateNbt().putInt("Netherited", MathHelper.clamp(netherited,0,yesNetherite));
    ///}
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean superHit = super.postHit(stack, target, attacker);
        StatusEffectInstance gooedEffect;
        ///if (stack.getItem()== ModItems.MARSHMALLOW_STICK) {
        ///    setCooked(stack,0);
        ///    setNetherite(stack,0);
        ///} else if (stack.getItem()==ModItems.COOKED_MARSHMALLOW_STICK){
        ///    setCooked(stack,1);
        ///    setNetherite(stack,0);
        ///}else if (stack.getItem()==ModItems.NETHERITE_MARSHMALLOW_STICK){
        ///    setCooked(stack,0);
        ///    setNetherite(stack,1);
        ///}else if (stack.getItem()==ModItems.COOKED_NETHERITE_MARSHMALLOW_STICK){
        ///    setCooked(stack,1);
        ///    setNetherite(stack,1);
        ///}
        int netheriteBooleanVar = stack.getOrCreateNbt().getInt("Netherited")+1;
        int cookedBooleanVar = stack.getOrCreateNbt().getInt("Cooked")+1;
        gooedEffect = new StatusEffectInstance(
                    new GooedEffect(StatusEffectCategory.HARMFUL, 2551000),
                    EnchantmentHelper.getLevel(new GooeynessEnchantment(), stack)*(10+2*netheriteBooleanVar)+10*cookedBooleanVar,
                    cookedBooleanVar*EnchantmentHelper.getLevel(new GooeynessEnchantment(), stack)
            );
        target.setStatusEffect(gooedEffect, attacker);
        return superHit;
    }
}
