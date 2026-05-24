package com.logic_thinkering.registration

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey



/**
 * Type alias for a function that initializes a block with settings and returns a `Block` instance.
 *
 * @param settings Block settings (used in block registration).
 * @return A newly created `Block` instance.
 */
typealias BlockInit = (Settings) -> Block
typealias Decorator = (Block) -> Block

@DslMarker
annotation class BlockRegistryDsl

/**
 * Registers a list of blocks in the Minecraft registry with a specified group and settings.
 *
 * @param init A lambda function used to initialize the `BlockRegistryBuilder` and register blocks.
 */
fun buildRegistryHelper(init: BlockRegistryBuilder.() -> Unit) : BlockRegistryHelper {
    val builder = BlockRegistryBuilder()
    builder.init()
    return builder.build()
}

/**
 * A builder class for registering blocks with specific settings.
 * It supports configuring settings, item group, and whether items should be registered for blocks.
 */
@BlockRegistryDsl
class BlockRegistryBuilder {
    private val blocks = mutableListOf<Pair<BlockInit, String>>()
    private var itemGroup: RegistryKey<ItemGroup>? = null
    private var baseSettings: Settings? = null
    private var registerItems = true
    private var decorator: Decorator? = null

    /**
     * Sets a decorator for the blocks being registered
     *
     * @param decorator the decorator to be applied to the blocks
     * @return The current instance of 'BlockRegistryBuilder'
     */
    fun decorator(decorator: Decorator) = apply { this.decorator = decorator }

    /**
     * Sets the settings for the blocks being registered.
     *
     * @param settings The settings to be applied to the blocks.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun settings(settings: Settings) = apply { this.baseSettings = settings }

    /**
     * Sets the item group that the block items will be added to.
     *
     * @param group The item group to add the block items to.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun group(group: RegistryKey<ItemGroup>) = apply { itemGroup = group }

    /**
     * Sets whether to register the items for each block.
     * defaults to True.
     *
     * @param group The item group to add the block items to.
     * @return The current instance of `BlockRegistryBuilder`.
     */
    fun registerItems(registerItems: Boolean) = apply { this.registerItems = registerItems }

    /**
     * Registers a block initialization function with a name.
     *
     * @param name The name for the block being registered.
     */
    infix fun BlockInit.with(name: String) {
        blocks += this to name
    }

    fun build() : BlockRegistryHelper {
        if (baseSettings == null)
            throw IllegalStateException("Settings must be set before block registration")
        return BlockRegistryHelper(blocks, itemGroup, baseSettings!!, registerItems, decorator)
    }
}
