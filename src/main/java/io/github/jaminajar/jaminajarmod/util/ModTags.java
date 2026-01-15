package io.github.jaminajar.jaminajarmod.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> FULL_SOUL_ITEMS =
            TagKey.of(RegistryKeys.ITEM, new Identifier("jaminajarmod", "full_soul_items"));
    public static final TagKey<Item> EMPTY_SOUL_ITEMS =
            TagKey.of(RegistryKeys.ITEM, new Identifier("jaminajarmod", "empty_soul_items"));
}
