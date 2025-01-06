package com.logic_thinkering.registration

import com.logic_thinkering.MOD_ID
import com.logic_thinkering.logger
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

class BlockRegistryHelper(
    private val blocks : List<Pair<BlockInit, String>>,
    private val itemGroup: RegistryKey<ItemGroup>?,
    private val baseSettings: AbstractBlock.Settings,
    private val registerItems: Boolean,
    private val decorator: Decorator?,
) {
    private fun registerBlock(init: BlockInit, name: String, registerItem: Boolean) : Pair<Block, String> {
        logger.info("Registering $name block")
        val id = Identifier.of(MOD_ID, name)
        val key = RegistryKey.of(RegistryKeys.BLOCK, id)
        val settings = baseSettings.registryKey(key)
        val block = init(settings)
        Registry.register(Registries.BLOCK, id, block).also {
            if (registerItem) {
                val itemKey = RegistryKey.of(RegistryKeys.ITEM, id)
                val itemSettings = Item.Settings().registryKey(itemKey)
                Registry.register(Registries.ITEM, id, BlockItem(it, itemSettings))
                ItemGroupEvents.modifyEntriesEvent(itemGroup).register {it.add(block)}
            }
        }

        return block to name
    }

    /**
     * Registers all blocks in the registry, using the provided settings and item group.
     * This method also ensures that the all the items will be registered for each block
     * if registerItems is set
     *
     * @throws IllegalStateException If settings are not defined before block registration.
     */
    fun register() {
        if (registerItems && itemGroup == null)
            throw IllegalStateException("Item group must be set before block item registration")
        if (decorator == null) {
            blocks.forEach {(init, name) -> registerBlock(init, name, registerItems)}
        } else {
            blocks
                .map { (init, name) -> registerBlock(init, name, false)}
                .forEach { (block, name) -> registerBlock({ decorator!!(block)}, name+"_decorated", registerItems) }
        }
    }
}