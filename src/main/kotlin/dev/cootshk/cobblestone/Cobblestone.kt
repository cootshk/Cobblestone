package dev.cootshk.cobblestone

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import org.jetbrains.annotations.VisibleForTesting

object Cobblestone {
    private val loader: FabricLoader = FabricLoader.getInstance()
    @VisibleForTesting
    internal fun getBundledMod(mod: String)
        = loader.getModContainer("cobblestone")
            .get().containedMods
            .firstOrNull {it.metadata.id == mod}

    @JvmStatic
    fun getBundledModVersion(mod: String)
        = getBundledMod(mod)
            ?.metadata?.version
            ?: throw ModNotBundledException("Mod $mod not bundled in Cobblestone!")

    @JvmStatic
    fun getBundledModVersion(mod: ModContainer)
        = getBundledModVersion(mod.metadata.id)
    @JvmStatic
    fun isBundledModLoaded(mod: String): Boolean {
        if (loader.getModContainer(mod).isEmpty) return false
        return (getBundledMod(mod) != null)
    }
    @JvmStatic
    fun isBundledModLoaded(mod: ModContainer)
        = isBundledModLoaded(mod.metadata.id)
}
