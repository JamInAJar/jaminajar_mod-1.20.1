package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.jaminajar.jaminajarmod.enchantment.BlastEnchantment;
import io.github.jaminajar.jaminajarmod.enchantment.PentaboomEnchantment;
import io.github.jaminajar.jaminajarmod.items.ModToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class BoomtubeItem extends AmmoItem {
    private final int maxAmmo;
    public int blastEnchant = 0;
    public int pentaboomCount=0;
    public int pentaboomEnable=0;
    public int explosionPower = 2;
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public BoomtubeItem(ModToolMaterials toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings, int maxAmmo) {
        super(settings, Items.GUNPOWDER, 32);
        this.attackDamage = attackDamage + toolMaterial.getAttackDamage();
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

    public void setPentaboomCounter(ItemStack stack, int pentaboomCounter){
        stack.getOrCreateNbt().putInt("Pentaboom", pentaboomCounter+1);
    }
    public int getPentaboom(ItemStack stack){
        return stack.getOrCreateNbt().getInt("Pentaboom");
    }
    public int getMaxAmmo(){
        return maxAmmo;
    }


    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean superHit = super.postHit(stack,target,attacker);
        blastEnchant = EnchantmentHelper.getLevel(new BlastEnchantment(), stack);
        if(EnchantmentHelper.getLevel(new PentaboomEnchantment(),stack)==1){
            pentaboomCount=getPentaboom(stack);}
        setPentaboomCounter(stack,pentaboomCount);
        if(pentaboomCount==5){
            pentaboomEnable=5;
            pentaboomCount=0;
        }else{
            pentaboomEnable=0;
        }
        explosionPower = 20*blastEnchant+4+25*pentaboomEnable;
        if (getAmmo(stack)<= maxAmmo && getAmmo(stack)!=0){
            target.getWorld().createExplosion(target,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    explosionPower,
                    false,
                    World.ExplosionSourceType.MOB);
            setAmmo(stack,getAmmo(stack)-1);
            return superHit;
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.jaminajarmod.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
