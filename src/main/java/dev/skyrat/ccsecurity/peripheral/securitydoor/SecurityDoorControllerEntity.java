package dev.skyrat.ccsecurity.peripheral.securitydoor;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import dan200.computercraft.shared.common.TileGeneric;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SecurityDoorControllerEntity extends TileGeneric implements IPeripheralTile {

    private final SecurityDoorControllerPeripheral peripheral;

    public SecurityDoorControllerEntity(BlockEntityType<? extends SecurityDoorControllerEntity> type) {
        super(type);
        this.peripheral = new Peripheral(this);
    }

    @Override
    public IPeripheral getPeripheral(@NotNull Direction direction) {
        return this.peripheral;
    }

    private static final class Peripheral extends SecurityDoorControllerPeripheral {
        private final SecurityDoorControllerEntity detector;

        private Peripheral(SecurityDoorControllerEntity detector) {
            this.detector = detector;
        }

        @Override
        public World getWorld() {
            return this.detector.getWorld();
        }

        @Override
        public Object getTarget() {
            return detector;
        }

        @Override
        public Vec3d getPosition() {
            BlockPos pos = this.detector.getPos();
            return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        }

        @Override
        public boolean equals(IPeripheral other) {
            return this == other || (other instanceof Peripheral && this.detector == ((Peripheral) other).detector);
        }
    }
}
