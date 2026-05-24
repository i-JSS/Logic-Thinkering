package com.logic_thinkering.weapon.decorator

import com.logic_thinkering.weapon.WeaponBehavior
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

abstract class WeaponDecorator(private val weapon: WeaponBehavior) : WeaponBehavior {
    override fun onHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return weapon.onHit(stack, target, attacker)
    }

    override fun appendWeaponTooltip(stack: ItemStack, world: Item.TooltipContext?, tooltip: MutableList<Text>, context: TooltipType) {
        weapon.appendWeaponTooltip(stack, world, tooltip, context)
    }
}