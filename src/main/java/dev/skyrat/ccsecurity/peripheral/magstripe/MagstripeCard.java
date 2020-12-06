package dev.skyrat.ccsecurity.peripheral.magstripe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MagstripeCard extends Item {

    public MagstripeCard(Settings settings) {
        super(settings);
    }

    public static String getValue(ItemStack stack) {
        return "";
    }
}
