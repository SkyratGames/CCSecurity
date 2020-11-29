package dev.skyrat.ccsecurity;

import dan200.computercraft.shared.util.FixedPointTileEntityType;
import dev.skyrat.ccsecurity.peripheral.entitydetector.EntityDetectorBlock;
import dev.skyrat.ccsecurity.peripheral.entitydetector.EntityDetectorBlockEntity;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CCSRegistry {
    public static void init() {
        Object[] o = {
                CCSBlockEntities.ENTITY_DETECTOR,
                CCSBlocks.ENTITY_DETECTOR,
                CCSItems.ENTITY_DETECTOR
        };
    }

    public static final String MOD_ID = "ccs";

    public static final class CCSBlocks {
        public static final EntityDetectorBlock ENTITY_DETECTOR = register("entity_detector",
                new EntityDetectorBlock(properties(), CCSBlockEntities.ENTITY_DETECTOR));

        private static Block.Settings properties() {
            return FabricBlockSettings.copyOf(Blocks.STONE)
                    .strength(2f);
        }

        private static <T extends Block> T register(String id, T value) {
            return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), value);
        }
    }

    public static final class CCSBlockEntities {
        public static final BlockEntityType<EntityDetectorBlockEntity> ENTITY_DETECTOR = ofBlock(() -> CCSBlocks.ENTITY_DETECTOR,
                "entity_detector",
                EntityDetectorBlockEntity::new);

        private static <T extends BlockEntity> BlockEntityType<T> ofBlock(Supplier<Block> block, String id, Function<BlockEntityType<T>, T> factory) {
            return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, id),
                    FixedPointTileEntityType.create(Objects.requireNonNull(block), factory));
        }
    }

    public static final class CCSItems {
        private static ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "general"), () -> new ItemStack(CCSBlocks.ENTITY_DETECTOR, 69));

        public static final BlockItem ENTITY_DETECTOR = ofBlock(CCSBlocks.ENTITY_DETECTOR, BlockItem::new);

        private static <B extends Block, I extends Item> I ofBlock(B parent, BiFunction<B, Item.Settings, I> supplier) {
            return Registry.register(Registry.ITEM, Registry.BLOCK.getId(parent), supplier.apply(parent, properties()));
        }

        private static Item.Settings properties() {
            return new Item.Settings().group(ITEM_GROUP);
        }
    }
}
