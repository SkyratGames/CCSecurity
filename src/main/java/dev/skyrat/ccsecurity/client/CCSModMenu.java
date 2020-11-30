package dev.skyrat.ccsecurity.client;

import dev.skyrat.ccsecurity.CCSConfig;
import dev.skyrat.ccsecurity.CCSRegistry;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CCSModMenu implements ModMenuApi {
    @Override
    public String getModId() {
        return CCSRegistry.MOD_ID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(CCSConfig.class, parent).get();
    }
}
