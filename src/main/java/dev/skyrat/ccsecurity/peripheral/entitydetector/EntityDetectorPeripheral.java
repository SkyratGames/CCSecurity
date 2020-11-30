package dev.skyrat.ccsecurity.peripheral.entitydetector;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dev.skyrat.ccsecurity.CCSecurity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class EntityDetectorPeripheral implements IPeripheral {

    protected EntityDetectorPeripheral() {
    }

    @Override
    public @NotNull String getType() {
        return "ccs_entity_detector";
    }

    @Override
    public void attach(@NotNull IComputerAccess computer) {
    }

    @Override
    public void detach(@NotNull IComputerAccess computer) {
    }

    public abstract World getWorld();

    public abstract Vec3d getPosition();

    @LuaFunction
    public final Object[] scan(boolean withPlayers) {
        Vec3d pos = getPosition();
        int range = CCSecurity.Config.entityDetector.range;
        boolean detectInvisible = CCSecurity.Config.entityDetector.detectInvisible;

        Vec3d box1 = new Vec3d(
                pos.x - range,
                pos.y - range,
                pos.z - range
        );
        Vec3d box2 = new Vec3d(
                pos.x + range,
                pos.y + range,
                pos.z + range
        );
        List<Entity> ents = getWorld().getEntitiesByClass(Entity.class, new Box(box1, box2), (Entity e) -> {
            if (!detectInvisible) {
                if (e.isInvisible())
                    return false;
            }
            if (!withPlayers) {
                return !(e instanceof PlayerEntity);
            }
            return true;
        });

        ArrayList<HashMap<String, Object>> output = new ArrayList<>();

        ents.forEach(entity -> {
            HashMap<String, Object> ent = new HashMap<>();
            ent.put("distance", entity.getPos().distanceTo(getPosition()));
            ent.put("isPlayer", entity instanceof PlayerEntity);
            ent.put("isLiving", entity instanceof LivingEntity);
            output.add(ent);
        });

        return new Object[]{output.toArray()};
    }
}
