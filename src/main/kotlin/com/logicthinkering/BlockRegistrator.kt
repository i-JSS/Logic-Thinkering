package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

fun registerBlocks(init: BlockRegistryBuilder.() -> Unit) {
    val builder = BlockRegistryBuilder()
    builder.init()
    builder.register()
}

typealias BlockInit = (Settings) -> Block
class BlockRegistryBuilder {
    private val blocks = mutableListOf<Pair<BlockInit, String>>()
    private var itemGroup : RegistryKey<ItemGroup> = ItemGroups.REDSTONE
    private var settings : Settings? = null
    private var registerItems : Boolean = true

    fun settings(settings: Settings): BlockRegistryBuilder {
        this.settings = settings
        return this
    }

    fun group(group: RegistryKey<ItemGroup>): BlockRegistryBuilder {
        itemGroup = group
        return this
    }

    infix fun BlockInit.with(name: String) {
        blocks += this to name
    }

    fun register() {
        if (settings == null)
            throw IllegalStateException("Settings must be set before block registration")

        val constructedBlocks = blocks.map { it.first(settings!!) to it.second}
        val registeredBlocks = constructedBlocks.map { (block, name) ->
            val id = Identifier.of(MOD_ID, name)
            Registry.register(Registries.BLOCK, id, block).also {
                if (registerItems) {
                    Registry.register(Registries.ITEM, id, BlockItem(it, Item.Settings()))
                }
            }
        }
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register {
            registeredBlocks.forEach { block -> it.add(block) }
        }
    }
}