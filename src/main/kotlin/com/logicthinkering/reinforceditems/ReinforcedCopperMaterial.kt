package com.logicthinkering.reinforceditems

import net.minecraft.block.Block
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

object ReinforcedCopperMaterial : ToolMaterial {
    override fun getDurability(): Int = 455
    override fun getMiningSpeedMultiplier(): Float = 5.0F
    override fun getAttackDamage(): Float = 4.0F
    override fun getInverseTag(): TagKey<Block> = BlockTags.INCORRECT_FOR_DIAMOND_TOOL
    override fun getEnchantability(): Int = 20
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(Items.COPPER_INGOT)
}