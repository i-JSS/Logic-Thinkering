package com.logic_thinkering.items

import com.logic_thinkering.MOD_ID
import com.logic_thinkering.weapon.WeaponBehavior
import com.logic_thinkering.weapon.decorator.ChargeableWeaponDecorator
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class ReinforcedCopperSword(
    material: ToolMaterial = ToolMaterial(
        BlockTags.INCORRECT_FOR_STONE_TOOL,
        200,
        6.5f,
        2.0f,
        14,
        ItemTags.STONE_TOOL_MATERIALS),
    name: String = "reinforced_copper_sword",
    settings: Settings = Settings().registryKey(
        RegistryKey.of(
            RegistryKeys.ITEM,
            Identifier.of(MOD_ID, name)
        )
    )
) : SwordItem(material, 4.0F, 5.0F, settings), WeaponBehavior {

    private val decorator: WeaponBehavior = ChargeableWeaponDecorator(this, 2, 4.0F)

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return decorator.onHit(stack, target, attacker)
    }

    override fun onHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return super.postHit(stack, target, attacker)
    }

    override fun appendWeaponTooltip(
        stack: ItemStack,
        world: TooltipContext?,
        tooltip: MutableList<Text>,
        context: TooltipType
    ) {
        super.appendTooltip(stack, world, tooltip, context)
    }
}