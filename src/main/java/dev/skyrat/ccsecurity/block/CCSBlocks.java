package dev.skyrat.ccsecurity.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CCSBlocks {
    public static final Block ENTITY_DETECTOR = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));


    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier())
    }
}
