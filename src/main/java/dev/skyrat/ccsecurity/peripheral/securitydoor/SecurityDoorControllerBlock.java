package dev.skyrat.ccsecurity.peripheral.securitydoor;

import dan200.computercraft.shared.common.BlockGeneric;
import dan200.computercraft.shared.common.TileGeneric;
import net.minecraft.block.entity.BlockEntityType;

public class SecurityDoorControllerBlock extends BlockGeneric {
    public SecurityDoorControllerBlock(Settings settings, BlockEntityType<? extends TileGeneric> type) {
        super(settings, type);
    }
}
