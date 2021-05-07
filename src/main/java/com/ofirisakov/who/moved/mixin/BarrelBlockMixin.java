package com.ofirisakov.who.moved.mixin;

import com.ofirisakov.who.moved.events.BlockWithEntityCallback;

import net.minecraft.block.BlockState;
import net.minecraft.block.BarrelBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BarrelBlock.class)
public class BarrelBlockMixin {
    @Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/block/BarrelBlock;onUse()V"), method = "onUse", cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit,
        CallbackInfoReturnable<ActionResult> info) {
        ActionResult result = BlockWithEntityCallback.EVENT.invoker().openBlockWithEntity(state, world, pos, player,
                hand, hit);

        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
