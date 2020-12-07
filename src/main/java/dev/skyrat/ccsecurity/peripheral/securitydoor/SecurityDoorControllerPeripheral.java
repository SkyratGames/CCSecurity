package dev.skyrat.ccsecurity.peripheral.securitydoor;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public abstract class SecurityDoorControllerPeripheral implements IPeripheral {

    private final HashSet<BlockPos> Doors = new HashSet<>();

    protected SecurityDoorControllerPeripheral() {
    }

    @Override
    public @NotNull String getType() {
        return "ccs_door_controller";
    }

    @Override
    public void attach(@NotNull IComputerAccess computer) {
    }

    @Override
    public void detach(@NotNull IComputerAccess computer) {
    }

    public abstract World getWorld();

    public abstract Vec3d getPosition();

    public void addDoor(SecurityDoorBlock door) {
        //Doors.add(door);
    }

    public void removeDoor(SecurityDoorBlock door) {
        Doors.remove(door);
    }

    @LuaFunction()
    public final void toggleOpen() {

    }

    @LuaFunction()
    public final void toggleLock() {
        //Doors.forEach(door -> door.toggleLock());
    }

    @LuaFunction
    public final void setLock(boolean locked) {
        //Doors.forEach(door -> door.setLocked(locked));
    }

    @LuaFunction
    public final void setOpen(boolean open) {
        //Doors.forEach(door -> door.setOpen(open));
    }
}
