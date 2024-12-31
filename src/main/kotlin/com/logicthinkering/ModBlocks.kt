package com.logicthinkering

import com.logicthinkering.digitalcircuits.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroups

fun registerBlocks() {
    registerBlocks {
        group(ItemGroups.REDSTONE)
        settings(Settings.copy(Blocks.REPEATER))
        ::ORGate with "or_gate_block"
        ::ANDGate with "and_gate_block"
        ::XORGate with "xor_gate_block"
        ::NOTGate with "not_gate_block"
        ::NORGate with "nor_gate_block"
        ::NANDGate with "nand_gate_block"
        ::XNORGate with "xnor_gate_block"
    }
}