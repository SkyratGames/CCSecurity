package dev.skyrat.ccsecurity;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CCSecurity implements ModInitializer {

    public static CCSConfig Config;

    @Override
    public void onInitialize() {
        CCSRegistry.init();
        AutoConfig.register(CCSConfig.class, GsonConfigSerializer::new);
        Config = AutoConfig.getConfigHolder(CCSConfig.class).getConfig();
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}
