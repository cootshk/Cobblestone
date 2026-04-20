package dev.cootshk.cobblestone

import net.fabricmc.api.ClientModInitializer
import org.apache.logging.log4j.LogManager

class CobblestoneTestClient : ClientModInitializer {
    private val logger = LogManager.getLogger()
    override fun onInitializeClient() {
        logger.info("-----Cobblestone Testing-----")
        assert(Cobblestone.getBundledMod("cobblestone-test") == null) { "Bundled the test mod?" }
        logger.info(
            if (Cobblestone.isBundledModLoaded("sodium"))
            "sodium loaded" else "sodium external"
        )
        try {
            logger.info("Voxy version ${Cobblestone.getBundledModVersion("voxy")}.")
        } catch (e: ModNotBundledException) {
            logger.info("No Voxy loaded!")
        }
        logger.info("----------------")
    }
}