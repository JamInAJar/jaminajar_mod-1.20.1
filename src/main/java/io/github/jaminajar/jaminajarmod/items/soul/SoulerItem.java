package io.github.jaminajar.jaminajarmod.items.soul;

import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.SoulerBeamProjectile;
import io.github.jaminajar.jaminajarmod.entity.SoulerSoulProjectile;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class SoulerItem extends Item {


    public SoulerItem(Settings settings) {
        super(settings);

    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    // Main use logic
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int soulEnergy = 0;
        for (int i = 0; i < user.getInventory().size(); i++) {
            ItemStack stack1 = user.getInventory().getStack(i);
            if (stack1.getItem() == ModItems.FULL_SOUL_CANISTER) {
                soulEnergy++;
            }
        }


        if (user.isSneaking()) {
            if (soulEnergy > 0 && !world.isClient) {
                int canisterCount = 0;
                for (int i = 0; i < user.getInventory().size(); i++) {
                    ItemStack stack1 = user.getInventory().getStack(i);
                    if (stack1.getItem() == ModItems.FULL_SOUL_CANISTER) {
                        canisterCount++;
                        user.getInventory().setStack(i, new ItemStack(ModItems.EMPTY_SOUL_CANISTER));
                        if (canisterCount >= 3) break;
                    }
                }
                SoulerBeamProjectile beam = new SoulerBeamProjectile(ModEntities.SOULER_BEAM_PROJECTILE, world);
                beam.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 8.0F, 0.001F);
                beam.setOwner(user);
                beam.setNoGravity(true);
                beam.setCritical(true);
                beam.setConsumedCanisters(soulEnergy);
                world.spawnEntity(beam);
                user.getItemCooldownManager().set(this, 20);
            } else {
                user.sendMessage(Text.literal("No Soul Energy!"), true);
            }
        } else if (!world.isClient) {
                SoulerSoulProjectile soul = new SoulerSoulProjectile(ModEntities.SOULER_SOUL_PROJECTILE, world);
                soul.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.0F);
                soul.setOwner(user);
                soul.setNoGravity(true);
                soul.setCritical(false);

                world.spawnEntity(soul);
                user.getItemCooldownManager().set(this, 10);

        }

        return TypedActionResult.success(stack);
    }
}