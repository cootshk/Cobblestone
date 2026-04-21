package dev.cootshk.cobblestone

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.Version
import org.jetbrains.annotations.VisibleForTesting

object Cobblestone {
    private val loader: FabricLoader = FabricLoader.getInstance()
    /**
     * Returns the bundled [ModContainer], or `null` if none is found.
     */
    @VisibleForTesting
    internal fun getBundledMod(mod: String)
        = loader.getModContainer("cobblestone")
            .get().containedMods
            .firstOrNull {it.metadata.id == mod}

    /**
     * Gets the [Version] of a bundled Mod.
     * @throws ModNotBundledException if the bundled mod is either missing or overridden (see [isBundledModLoaded]).
     */
    @JvmStatic
    fun getBundledModVersion(mod: String): Version
        = getBundledMod(mod)
            ?.metadata?.version
            ?: throw ModNotBundledException("Mod $mod not bundled in Cobblestone!")

    /**
     * Gets the [Version] of a bundled Mod.
     * @throws ModNotBundledException if the bundled mod is either missing or overridden (see [isBundledModLoaded]).
     */
    @JvmStatic
    fun getBundledModVersion(mod: ModContainer)
        = getBundledModVersion(mod.metadata.id)

    /**
     * Returns `true` if the mod passed is loaded by Cobblestone (and not by a different JiJ/placed in the mods folder directly).
     */
    @JvmStatic
    fun isBundledModLoaded(mod: String): Boolean {
        if (loader.getModContainer(mod).isEmpty) return false
        return (getBundledMod(mod) != null)
    }

    /**
     * Returns `true` if the mod passed is loaded by Cobblestone (and not by a different JiJ/placed in the mods folder directly).
     */
    @JvmStatic
    fun isBundledModLoaded(mod: ModContainer)
        = isBundledModLoaded(mod.metadata.id)
}
