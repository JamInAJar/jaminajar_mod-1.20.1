package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntityAttributes {
    public static final EntityAttribute FLAMMABILITY = register(
            "generic.flammability", new ClampedEntityAttribute("attribute.name.generic.flammability", 0.0, -10.0, 10.0).setTracked(true)
    );
    private static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, id, attribute);
    }
}
