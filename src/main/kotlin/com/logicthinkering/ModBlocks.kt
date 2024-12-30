package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

private val logicGate = AndGateBlock(Settings.copy(Blocks.REPEATER))

fun registerBlocks() {
    registerBlock(logicGate, "logic_gate_block")
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register { it.add(logicGate) }
}

private fun registerBlock(block: Block, name: String, shouldRegisterItem : Boolean = true): Block? {
    val id = Identifier.of(MOD_ID, name)
    if (shouldRegisterItem) {
        val blockItem = BlockItem(block, Item.Settings())
        Registry.register(Registries.ITEM, id, blockItem)
    }

    return Registry.register(Registries.BLOCK, id, block)
}