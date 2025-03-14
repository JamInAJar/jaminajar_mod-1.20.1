package io.github.jaminajar.jaminajarmod.items.food;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import static net.minecraft.entity.effect.StatusEffects.*;

public class ModFoods {
    public static final FoodComponent MARSHMALLOW =
            new FoodComponent.Builder()
                    .hunger(3)
                    .saturationModifier(4)
                    .snack()
                    .statusEffect(new StatusEffectInstance(SPEED,5),1f)
                    .statusEffect(new StatusEffectInstance(ABSORPTION,20,1),1f)
                    .build();
    public static final FoodComponent COOKED_MARSHMALLOW =
            new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(8)
                    .snack()
                    .statusEffect(new StatusEffectInstance(FIRE_RESISTANCE,30),1f)
                    .statusEffect(new StatusEffectInstance(ABSORPTION,45,3),1f)
                    .build();
}
