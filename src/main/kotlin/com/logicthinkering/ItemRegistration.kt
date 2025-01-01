package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import kotlin.collections.plusAssign


/**
 * Registers a list of items in the Minecraft registry with a specified group and settings.
 *
 * @param init A lambda function used to initialize the `ItemRegistryBuilder` and register items.
 */
fun registerItems(init: ItemRegistryBuilder.() -> Unit) {
    val builder = ItemRegistryBuilder()
    builder.init()
    builder.register()
}

@DslMarker
annotation class ItemRegistryDsl

/**
 * A builder class for registering Items with specific settings and item groups.
 */
@ItemRegistryDsl
class ItemRegistryBuilder {
    private val items = mutableListOf<Pair<Item, String>>()
    private var itemGroup : RegistryKey<ItemGroup>? = null


    /**
     * Sets the item group that the items will be added to.
     *
     * @param group The group to add the items to.
     * @return The current instance of `ItemRegistryBuilder`.
     */
    fun group(group: RegistryKey<ItemGroup>): ItemRegistryBuilder {
        itemGroup = group
        return this
    }


    /**
     * Adds an item with a specific identifier to be registered.
     * The item name will be used in the registry.
     *
     * @param name The name for the item being registered.
     */
    infix fun Item.with(name: String) {
        items += this to name
    }

    fun register() {
        if (itemGroup == null)
            throw IllegalStateException("Item group must be set before block item registration")
        items.forEach { (item, name) ->
            Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item)
        }
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register {
            items.forEach { (item, _) ->  it.add(item) }
        }
    }

}