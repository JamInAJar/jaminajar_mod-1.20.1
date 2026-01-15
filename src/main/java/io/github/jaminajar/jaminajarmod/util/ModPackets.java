package io.github.jaminajar.jaminajarmod.util;

import io.github.jaminajar.jaminajarmod.items.custom.SwordAxeAxeItem;
import io.github.jaminajar.jaminajarmod.items.custom.SwordAxeSwordItem;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier SWITCH_GRIP_ID =
            new Identifier("jaminajarmod", "switch_grip");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SWITCH_GRIP_ID,
                (server, player, handler, buf, responseSender) -> {
                    server.execute(() -> {
                        ItemStack stack = player.getMainHandStack();

                        if (stack.getItem() instanceof SwordAxeAxeItem axeItem) {
                            axeItem.switchGrip(player, stack);
                        } else if (stack.getItem() instanceof SwordAxeSwordItem swordItem) {
                            swordItem.switchGrip(player, stack);
                        }
                    });
                });
    }
}