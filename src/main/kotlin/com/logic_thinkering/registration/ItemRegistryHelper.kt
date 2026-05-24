package com.logic_thinkering.registration

import com.logic_thinkering.MOD_ID
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

class ItemRegistryHelper(
    private val items: List<Pair<Item, String>>,
    private val itemGroup: RegistryKey<ItemGroup>,
) {
    fun register() {
        val group = checkNotNull(itemGroup) { "Item group must be set before registration" }
        items.forEach { (item, name) ->
            Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item)
        }
        ItemGroupEvents.modifyEntriesEvent(group).register {
            items.forEach { (item, _) ->  it.add(item) }
        }
    }

}