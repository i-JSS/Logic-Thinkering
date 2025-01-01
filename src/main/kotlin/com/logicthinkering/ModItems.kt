package com.logicthinkering

import com.logicthinkering.reinforceditems.ReinforcedCopperShield
import com.logicthinkering.reinforceditems.ReinforcedCopperSword
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