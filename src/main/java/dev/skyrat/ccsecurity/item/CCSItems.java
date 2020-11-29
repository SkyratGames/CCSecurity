package dev.skyrat.ccsecurity.item;

import dev.skyrat.ccsecurity.block.CCSBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.skyrat.ccsecurity.block.CCSBlocks.ENTITY_DETECTOR;

public class CCSItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("ccs","general"),
            () -> new ItemStack(CCSBlocks.ENTITY_DETECTOR));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("ccs","entity_detector"),
                new BlockItem(ENTITY_DETECTOR, new Item.Settings().group(ITEM_GROUP)));
    }
}
