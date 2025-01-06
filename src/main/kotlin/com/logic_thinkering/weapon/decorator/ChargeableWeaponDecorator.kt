package com.logic_thinkering.weapon.decorator

import com.logic_thinkering.ModComponents.CHARGE_COMPONENT
import com.logic_thinkering.weapon.WeaponBehavior
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.item.Item.TooltipContext
import net.minecraft.server.world.ServerWorld

class ChargeableWeaponDecorator(
    weapon: WeaponBehavior,
    private val maxCharge: Int = 3,
    private val baseAttackDamage: Float // Add this parameter
) : WeaponDecorator(weapon) {
    companion object {
        private const val DEFAULT_CHARGE = 0
        private const val SOUND_VOLUME = 1.0f
        private const val SOUND_PITCH = 1.0f
    }

    override fun onHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (attacker !is PlayerEntity) {
            return super.onHit(stack, target, attacker)
        }

        processChargedAttack(stack, target, attacker)
        return super.onHit(stack, target, attacker)
    }

    private fun processChargedAttack(stack: ItemStack, target: LivingEntity, attacker: PlayerEntity) {
        val currentCharge = getCurrentCharge(stack)
        if (currentCharge <= 0) return

        dealChargedDamage(target, attacker, currentCharge)
        resetCharge(stack)
        playAttackSound(target, attacker)
    }

    private fun getCurrentCharge(stack: ItemStack): Int =
        stack.get(CHARGE_COMPONENT) ?: DEFAULT_CHARGE

    private fun dealChargedDamage(target: LivingEntity, attacker: PlayerEntity, chargeLevel: Int) {
        // Use the baseAttackDamage parameter instead of trying to cast
        val damageDealt = baseAttackDamage * (kotlin.math.max(chargeLevel, maxCharge) + 1)
        val world = target.world
        target.damage(world as ServerWorld,target.damageSources.playerAttack(attacker), damageDealt)
    }

    private fun resetCharge(stack: ItemStack) {
        stack.set(CHARGE_COMPONENT, DEFAULT_CHARGE)
    }

    private fun playAttackSound(target: LivingEntity, attacker: PlayerEntity) {
        attacker.world.playSound(
            null,
            target.x,
            target.y,
            target.z,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundCategory.PLAYERS,
            SOUND_VOLUME,
            SOUND_PITCH
        )
    }

    override fun appendWeaponTooltip(stack: ItemStack, world: TooltipContext?, tooltip: MutableList<Text>, context: TooltipType) {
        super.appendWeaponTooltip(stack, world, tooltip, context)
        addChargeTooltip(stack, tooltip)
    }

    private fun addChargeTooltip(stack: ItemStack, tooltip: MutableList<Text>) {
        val chargeLevel = getCurrentCharge(stack)
        if (chargeLevel > 0) {
            tooltip.add(
                Text.translatable("item.logic_thinkering.counter.info", chargeLevel)
                    .formatted(Formatting.GOLD)
            )
        }
    }
}