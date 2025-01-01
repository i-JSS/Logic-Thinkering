package com.logicthinkering.mixin;

import com.logicthinkering.reinforceditems.ReinforcedCopperShield;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof PlayerEntity player) {
            ItemStack activeItem = player.getActiveItem();
            if (activeItem.getItem() instanceof ReinforcedCopperShield shield && player.isBlocking()) {
                shield.handleBlockedDamage(player, amount);

                cir.setReturnValue(false);
            }
        }
    }
}