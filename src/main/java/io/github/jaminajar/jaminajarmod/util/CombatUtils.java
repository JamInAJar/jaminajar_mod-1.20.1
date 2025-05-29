package io.github.jaminajar.jaminajarmod.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public final class CombatUtils {
    private CombatUtils() {}

    public static boolean damageIgnoringIFrames(LivingEntity target, DamageSource source, float amount) {
        target.timeUntilRegen = 0;
        target.hurtTime = 0;

        // Apply damage
        return target.damage(source, amount);
    }
}

