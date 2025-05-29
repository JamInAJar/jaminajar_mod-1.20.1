package io.github.jaminajar.jaminajarmod.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import io.github.jaminajar.jaminajarmod.items.ModItems;

public class ModLootTableModifiers {
    private static final Identifier RAVAGER_ID =
            new Identifier("minecraft","entities/ravager");
    private static final Identifier WARDEN_ID=
            new Identifier("minecraft", "entities/warden");
    private static final Identifier DUNGEON_CHEST_ID =
            new Identifier("minecraft","chests/simple_dungeon");
    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (RAVAGER_ID.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.33f))
                        .with(ItemEntry.builder(ModItems.RAVAGER_TOOTH))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f,2.0f)));
                tableBuilder.pool(poolBuilder.build());
            }
            if (WARDEN_ID.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.BIOSPEAKER))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f,1.0f)));
                tableBuilder.pool(poolBuilder.build());
            }
            if(DUNGEON_CHEST_ID.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.DRIPSTONER_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f,1.0f)));
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
