package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

private val logicSettings = Settings.copy(Blocks.REPEATER)
private val blocksToRegister = listOf(
    ORGate(logicSettings) to "or_gate_block",
    ANDGate(logicSettings) to "and_gate_block",
    XORGate(logicSettings) to "xor_gate_block",
    NOTGate(logicSettings) to "not_gate_block",
    NORGate(logicSettings) to "nor_gate_block",
    NANDGate(logicSettings) to "nand_gate_block",
    XNORGate(logicSettings) to "xnor_gate_block",
)

fun registerBlocks() {
    val registeredBlocks = blocksToRegister.map { (block, name) ->
        val id = Identifier.of(MOD_ID, name)
        Registry.register(Registries.BLOCK, id, block).also {
            Registry.register(Registries.ITEM, id, BlockItem(it, Item.Settings()))
        }
    }
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register {
        registeredBlocks.forEach { block -> it.add(block) }
    }
}