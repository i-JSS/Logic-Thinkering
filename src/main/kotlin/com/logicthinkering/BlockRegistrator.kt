package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

/**
 * Registers a list of blocks in the Minecraft registry with a specified group and settings.
 *
 * @param init A lambda function used to initialize the `BlockRegistryBuilder` and register blocks.
 */
fun registerBlocks(init: BlockRegistryBuilder.() -> Unit) {
    val builder = BlockRegistryBuilder()
    builder.init()
    builder.register()
}

/**
 * Type alias for a function that initializes a block with settings and returns a `Block` instance.
 *
 * @param settings Block settings (used in block registration).
 * @return A newly created `Block` instance.
 */
typealias BlockInit = (Settings) -> Block


/**
 * A builder class for registering blocks with specific settings and item groups.
 * It supports configuring settings, item group, and whether items should be registered for blocks.
 */
class BlockRegistryBuilder {
    private val blocks = mutableListOf<Pair<BlockInit, String>>()
    // TODO: remove default item group
    private var itemGroup : RegistryKey<ItemGroup> = ItemGroups.REDSTONE
    private var settings : Settings? = null
    private var registerItems : Boolean = true

    /**
     * Sets the settings for the blocks being registered.
     *
     * @param settings The settings to be applied to the blocks.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun settings(settings: Settings): BlockRegistryBuilder {
        this.settings = settings
        return this
    }


    /**
     * Sets the item group that the block items will be added to.
     *
     * @param group The item group to add the block items to.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun group(group: RegistryKey<ItemGroup>): BlockRegistryBuilder {
        itemGroup = group
        return this
    }

    /**
     * Sets whether to register the items for each block.
     * defaults to True.
     *
     * @param group The item group to add the block items to.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun registerItems(registerItems: Boolean) : BlockRegistryBuilder {
        this.registerItems = registerItems
        return this
    }

    /**
     * Registers a block initialization function with a name. This function is used to build and register a block.
     * The block name will be used in the registry.
     *
     * @param name The name for the block being registered.
     */
    infix fun BlockInit.with(name: String) {
        blocks += this to name
    }

    /**
     * Registers all blocks in the registry, using the provided settings and item group.
     * This method also ensures that the all the items will be registered for each block
     * if registerItems is set
     *
     * @throws IllegalStateException If settings are not defined before block registration.
     */
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