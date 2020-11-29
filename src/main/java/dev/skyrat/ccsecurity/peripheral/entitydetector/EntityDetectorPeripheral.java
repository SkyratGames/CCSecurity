package dev.skyrat.ccsecurity.peripheral.entitydetector;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.core.IComputer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class EntityDetectorPeripheral implements IPeripheral {
    private final Set<IComputerAccess> computers = new HashSet<>();
    private final Set<Integer> entities = new HashSet<>();

    protected EntityDetectorPeripheral() {
    }

    @Override
    public String getType() {
        return "ccs_entity_detector";
    }

    @Override
    public void attach(@NotNull IComputerAccess computer) {
        this.computers.add(computer);
    }

    @Override
    public void detach(@NotNull IComputerAccess computer) {
        this.computers.remove(computer);
    }

    public void update() {
        if(getWorld().isClient())
            return;

        Vec3d pos = getPosition();
        int range = 4;

        Vec3d box1 = new Vec3d(
                pos.x-range,
                pos.y-range,
                pos.z-range
        );
        Vec3d box2 = new Vec3d(
                pos.x+range,
                pos.y+range,
                pos.z+range
        );

        List<LivingEntity> living = getWorld().getEntitiesByClass(LivingEntity.class, new Box(box1, box2), (LivingEntity e) -> true);
        //if(living.isEmpty())
        //    return;
        // RIP game breaking bug <3 you were hated but also loved by many.

        // Bash solved my little manlet brain problem
        // Thanks daddy <3
        living.forEach(entity -> {
            if(!entities.contains(entity.getEntityId())) {
                computers.iterator().forEachRemaining(c -> c.queueEvent("ccs_entity_detection", true, entity.getName().asString(), entity.getUuidAsString()));
                entities.add(entity.getEntityId());
            }
        });
        entities.forEach(id -> {
            LivingEntity entity = (LivingEntity) getWorld().getEntityById(id);
            if(entity == null) {
                entities.remove(id);
                return;
            }
            if(living.stream().noneMatch(e -> e.getEntityId() == id)) {
               computers.iterator().forEachRemaining(c -> c.queueEvent("ccs_entity_detection", false, entity.getName().asString(), entity.getUuidAsString()));
               entities.remove(id);
           }
        });

    }

    public abstract World getWorld();
    public abstract Vec3d getPosition();

    @LuaFunction
    public final String test(ILuaContext ctx, String text) {
        return text.toUpperCase();
    }
}
