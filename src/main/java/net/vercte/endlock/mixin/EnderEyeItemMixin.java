package net.vercte.endlock.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.vercte.endlock.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin {
    @Inject(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"), cancellable = true)
    public void popEye(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if(!Config.lockEnd) return;
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand().copyWithCount(1);
        Player player = context.getPlayer();

        if(player != null) player.displayClientMessage(Component.translatable("endlock.locked"), true);

        context.getItemInHand().shrink(1);

        Vec3 vec = pos.getCenter();
        ItemEntity entity = new ItemEntity(context.getLevel(), vec.x, vec.y + 0.4, vec.z, stack);
        entity.setDeltaMovement(0, 1/4d, 0);
        entity.setPickUpDelay(20);
        level.addFreshEntity(entity);

        level.levelEvent(1503, pos, 0);

        cir.setReturnValue(InteractionResult.CONSUME);
    }
}
