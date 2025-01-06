package com.logic_thinkering.registration

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import kotlin.collections.plusAssign


/**
 * Registers a list of items in the Minecraft registry with a specified group and settings.
 *
 * @param init A lambda function used to initialize the `ItemRegistryBuilder` and register items.
 */
fun registerItems(init: ItemRegistryBuilder.() -> Unit) : ItemRegistryHelper {
    val builder = ItemRegistryBuilder()
    builder.init()
    return builder.build()
}

@DslMarker
annotation class ItemRegistryDsl

/**
 * A builder class for registering Items with specific settings and item groups.
 */
@ItemRegistryDsl
class ItemRegistryBuilder {
    private val items = mutableListOf<Pair<Item, String>>()
    private var itemGroup: RegistryKey<ItemGroup>? = null

    /**
     * Sets the item group that the items will be added to.
     *
     * @param group The group to add the items to.
     * @return The current instance of `ItemRegistryBuilder`.
     */
    fun group(group: RegistryKey<ItemGroup>) = apply { itemGroup = group }

    /**
     * Adds an item with a specific identifier to be registered.
     *
     * @param name The name for the item being registered.
     */
    infix fun Item.with(name: String) {
        items += this to name
    }

    fun build() : ItemRegistryHelper {
        if (itemGroup == null)
            throw IllegalStateException("Item group must be set before registration")
        return ItemRegistryHelper(items, itemGroup!!)
    }
}