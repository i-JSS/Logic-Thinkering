package com.logicthinkering

import com.logicthinkering.LogicThinkering.MOD_ID
import com.mojang.serialization.Codec
import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


val CHARGE_COMPONENT: ComponentType<Int> = Registry.register(
    Registries.DATA_COMPONENT_TYPE,
    Identifier.of(MOD_ID, "charge_level"),
    ComponentType.builder<Int>().codec(Codec.INT).build()
)

