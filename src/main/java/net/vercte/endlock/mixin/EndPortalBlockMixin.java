package net.vercte.endlock.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.vercte.endlock.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void refuse(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if(Config.lockEnd) ci.cancel();
    }

    @Inject(method = "animateTick", at = @At("HEAD"), cancellable = true)
    public void noParticles(BlockState p_221102_, Level p_221103_, BlockPos p_221104_, RandomSource p_221105_, CallbackInfo ci) {
        if(Config.lockEnd) ci.cancel();
    }
}
