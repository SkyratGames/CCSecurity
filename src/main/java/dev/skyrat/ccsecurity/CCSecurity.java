package dev.skyrat.ccsecurity;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class CCSecurity implements ModInitializer {

    public static CCSConfig Config;

    @Override
    public void onInitialize() {
        CCSRegistry.init();
        AutoConfig.register(CCSConfig.class, GsonConfigSerializer::new);
        Config = AutoConfig.getConfigHolder(CCSConfig.class).getConfig();
    }
}
