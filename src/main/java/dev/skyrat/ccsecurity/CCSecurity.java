package dev.skyrat.ccsecurity;

import dev.skyrat.ccsecurity.block.CCSBlocks;
import dev.skyrat.ccsecurity.item.CCSItems;
import net.fabricmc.api.ModInitializer;

public class CCSecurity implements ModInitializer {

    @Override
    public void onInitialize() {
        CCSBlocks.registerBlocks();
        CCSItems.registerItems();
    }
}
