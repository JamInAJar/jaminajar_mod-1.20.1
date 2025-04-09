package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.jaminajar.jaminajarmod.enchantment.BlastEnchantment;
import io.github.jaminajar.jaminajarmod.items.ModToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class BoomtubeItem extends ToolItem {
    private final int maxGunpowder;
    public int enchant = 0;
    public int explosionPower = 2;
    String gunpowderTooltipDisplay = "";
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public BoomtubeItem(ModToolMaterials toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings, int maxGunpowder) {
        super(toolMaterial, settings);
        this.attackDamage = attackDamage + toolMaterial.getAttackDamage();




        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID,
                "Weapon modifier", attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
                "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        this.maxGunpowder = maxGunpowder;
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            stack = user.getStackInHand(hand);
            int currentGunpowder = getGunpowder(stack);
            if (currentGunpowder < maxGunpowder && playerHasItem(user, Items.GUNPOWDER)) {
                setGunpowder(stack, currentGunpowder + 1);
                reduceItem(user, Items.GUNPOWDER, 1);
                return TypedActionResult.success(stack);
            }
            return TypedActionResult.pass(stack);
        }
        return TypedActionResult.fail(stack);
    }

    public int getGunpowder(ItemStack stack){
        return stack.getOrCreateNbt().getInt("Gunpowder");
    }
    public void setGunpowder(ItemStack stack, int storedGunpowder){
        stack.getOrCreateNbt().putInt("Gunpowder", MathHelper.clamp(storedGunpowder,0,maxGunpowder));
        gunpowderTooltipDisplay = "Gunpowder: " + storedGunpowder;

    }
    public int getMaxGunpowder(){
        return maxGunpowder;
    }
    public boolean isItemBarVisible(ItemStack stack) {
        return getGunpowder(stack) > 0;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        int charge = getGunpowder(stack);
        return MathHelper.ceil(charge * 13.0F / maxGunpowder);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int charge = getGunpowder(stack);
        return charge == 0 ? 0x220000 : 0xFFFF00;
    }

    public static boolean playerHasItem(PlayerEntity player, Item itemToCheck) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == itemToCheck) {
                return true;
            }
        }
        return false;
    }
    public static void reduceItem(PlayerEntity player, Item itemToReduce, int amount) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == itemToReduce) {
                int currentCount = stack.getCount();
                if (currentCount >= amount) {
                    stack.decrement(amount);
                }
                return;
            }
        }
    }


    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean superHit = super.postHit(stack,target,attacker);
        enchant = EnchantmentHelper.getLevel(new BlastEnchantment(), stack);
        explosionPower = 2*enchant+2;
        if (getGunpowder(stack)<=maxGunpowder && getGunpowder(stack)!=0){
            target.getWorld().createExplosion(target,
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    explosionPower,
                    false,
                    World.ExplosionSourceType.MOB);
            setGunpowder(stack,getGunpowder(stack)-1);
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
