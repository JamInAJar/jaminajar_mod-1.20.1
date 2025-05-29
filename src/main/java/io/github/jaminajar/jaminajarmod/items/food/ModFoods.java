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
                    .statusEffect(new StatusEffectInstance(SPEED,400),1f)
                    .statusEffect(new StatusEffectInstance(ABSORPTION,200,1),1f)
                    .alwaysEdible()
                    .build();
    public static final FoodComponent COOKED_MARSHMALLOW =
            new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(8)
                    .snack()
                    .statusEffect(new StatusEffectInstance(FIRE_RESISTANCE,1000),1f)
                    .statusEffect(new StatusEffectInstance(ABSORPTION,400,2),1f)
                    .statusEffect(new StatusEffectInstance(REGENERATION,40,3),1f)
                    .alwaysEdible()
                    .build();
}
