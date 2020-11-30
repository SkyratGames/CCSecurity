package dev.skyrat.ccsecurity.peripheral.entitydetector;

import dan200.computercraft.shared.common.BlockGeneric;
import dan200.computercraft.shared.common.TileGeneric;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;

public class EntityDetectorBlock extends BlockGeneric {


    public EntityDetectorBlock(Settings settings, BlockEntityType<? extends TileGeneric> type) {
        super(settings, type);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
