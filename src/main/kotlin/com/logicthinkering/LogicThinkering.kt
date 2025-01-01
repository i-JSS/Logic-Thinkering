package com.logicthinkering

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Object responsible for initializing the mod.
 * Registering blocks, items and componentes and setting the mod's identifier.
 */
object LogicThinkering : ModInitializer {
    const val MOD_ID = "logic-thinkering"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing Logic Thinkering mod!")
        registerBlocks()
        ModItems.registerItems()
        ModComponents.initialize()
    }
}
