package com.logicthinkering.reinforceditems

import com.logicthinkering.CHARGE_COMPONENT
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.max

class ReinforcedCopperSword(
    private val material: ReinforcedCopperMaterial = ReinforcedCopperMaterial,
    settings: Settings = Settings()
) : SwordItem(material, settings) {

    companion object {
        private const val DEFAULT_CHARGE = 0
        private const val SOUND_VOLUME = 1.0f
        private const val SOUND_PITCH = 1.0f
        private const val MAX_CHARGE = 2
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (attacker !is PlayerEntity) {
            return super.postHit(stack, target, attacker)
        }

        processChargedAttack(stack, target, attacker)
        return super.postHit(stack, target, attacker)
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
        val damageDealt = material.attackDamage * (max(chargeLevel, MAX_CHARGE) + 1)
        target.damage(target.damageSources.playerAttack(attacker), damageDealt)
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

    override fun appendTooltip(
        stack: ItemStack,
        world: TooltipContext?,
        tooltip: MutableList<Text>,
        context: TooltipType
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        addChargeTooltip(stack, tooltip)
    }

    private fun addChargeTooltip(stack: ItemStack, tooltip: MutableList<Text>) {
        val chargeLevel = getCurrentCharge(stack)

        if (chargeLevel > 0) {
            tooltip.add(
                Text.translatable("item.logic-thinkering.counter.info", chargeLevel)
                    .formatted(Formatting.GOLD)
            )
        }
    }
}