package dev.skyrat.ccsecurity;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = CCSRegistry.MOD_ID)
public class CCSConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public EntityDetector entityDetector = new EntityDetector();

    public static class EntityDetector {
        public boolean detectInvisible = false;
        public int range = 16;
    }
}
