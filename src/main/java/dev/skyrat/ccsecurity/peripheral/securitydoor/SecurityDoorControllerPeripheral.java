package dev.skyrat.ccsecurity.peripheral.securitydoor;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dev.skyrat.ccsecurity.CCSRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.security.Security;
import java.util.HashMap;

public abstract class SecurityDoorControllerPeripheral implements IPeripheral {

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

    private void openDoor(BlockPos pos, boolean open) {
        World world = getWorld();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(block instanceof SecurityDoorBlock)
            ((SecurityDoorBlock) block).setOpen(world, state, pos, open);

        if(block instanceof DoorBlock)
            ((DoorBlock) block).setOpen(world, state, pos, open);
    }
    private void lockDoor(BlockPos pos, boolean lock) {
        World world = getWorld();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(block instanceof SecurityDoorBlock)
            ((SecurityDoorBlock) block).setLocked(world, state, pos, lock);
    }

    private boolean isDoor(BlockPos pos) {
        return isDoor(getWorld().getBlockState(pos).getBlock());
    }

    private boolean isDoor(Block block) {
        return block instanceof DoorBlock || block instanceof SecurityDoorBlock;
    }

    private BlockPos findAdjacentDoor(BlockPos controllerPos) {
        World world = getWorld();
        for (int i = 0; i < 3; i++) {
            Direction dir = Direction.fromHorizontal(i);
            BlockPos newControllerPos = controllerPos.offset(dir);
            BlockPos newDoorPos = newControllerPos.up();
            Block doorBlock = world.getBlockState(newControllerPos).getBlock();
            if (doorBlock instanceof SecurityDoorControllerBlock && isDoor(newDoorPos)) {
                return newDoorPos;
            }
        }
        return null;
    }

    @LuaFunction
    public final void setLock(boolean locked) {
        BlockPos controllerPos = new BlockPos(getPosition());
        BlockPos ourDoor = controllerPos.up();
        BlockPos neighborDoor = findAdjacentDoor(controllerPos);
        lockDoor(ourDoor, locked);
        lockDoor(neighborDoor, locked);
    }

    @LuaFunction
    public final void setOpen(boolean open) {
        World world = getWorld();
        BlockPos controllerPos = new BlockPos(getPosition());
        BlockPos ourDoor = controllerPos.up();
        BlockPos neighborDoor = findAdjacentDoor(controllerPos);
        openDoor(ourDoor, open);
        openDoor(neighborDoor, open);
    }

    @LuaFunction
    public final void lock() {
        setLock(true);
    }

    @LuaFunction
    public final void unlock() {
        setLock(false);
    }

    @LuaFunction
    public final void open() {
        setOpen(true);
    }

    @LuaFunction
    public final void close() {
        setOpen(false);
    }

    @LuaFunction
    public final boolean isOpen() throws LuaException {
        World world = getWorld();
        BlockPos controllerPos = new BlockPos(getPosition());
        BlockPos door = controllerPos.up();
        BlockState state = world.getBlockState(door);
        Block doorBlock = state.getBlock();

        if(doorBlock instanceof DoorBlock)
            return state.get(DoorBlock.OPEN);

        if(doorBlock instanceof SecurityDoorBlock)
            return state.get(SecurityDoorBlock.OPEN);

        throw new LuaException("Block above controller is not a door.");
    }

    @LuaFunction
    public final boolean isLocked() throws LuaException {
        World world = getWorld();
        BlockPos controllerPos = new BlockPos(getPosition());
        BlockPos door = controllerPos.up();
        BlockState state = world.getBlockState(door);
        Block doorBlock = state.getBlock();

        if(doorBlock instanceof SecurityDoorBlock)
            return state.get(SecurityDoorBlock.LOCKED);

        throw new LuaException("Block above controller is not a Security Door.");
    }
}
