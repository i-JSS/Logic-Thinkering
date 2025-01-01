package com.logicthinkering

import net.minecraft.item.ItemGroups

object ModItems {
    fun registerItems() {
        registerItems {
            group(ItemGroups.COMBAT)
            ReinforcedCopperShield() with "reinforced_copper_shield"
            ReinforcedCopperSword() with "reinforced_sword"
        }
    }
}