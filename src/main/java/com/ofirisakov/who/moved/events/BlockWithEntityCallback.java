package com.ofirisakov.who.moved.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockWithEntityCallback {
    Event<BlockWithEntityCallback> EVENT = EventFactory.createArrayBacked(BlockWithEntityCallback.class,
            (listeners) -> (state, world, pos, player, hand, hit) -> {
                for (BlockWithEntityCallback listener : listeners) {
                    ActionResult result = listener.openBlockWithEntity(state, world, pos, player, hand, hit);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult openBlockWithEntity(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit);
}
