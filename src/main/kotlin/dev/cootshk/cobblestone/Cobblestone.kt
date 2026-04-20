package dev.cootshk.cobblestone

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.Version
import net.fabricmc.loader.impl.util.version.StringVersion
import kotlin.jvm.optionals.getOrElse

object Cobblestone {
    private val loader: FabricLoader = FabricLoader.getInstance()
    private val mcVersion: String = loader.getModContainer("minecraft").get().metadata.version.friendlyString
    init {
        println("[Cobblestone] Loading for Minecraft v$mcVersion!")
    }
    fun getBundledModVersion(mod: String): Version {
        return StringVersion(
            this.javaClass.getResource("/included-mods/$mcVersion/$mod.modver")?.readText()
                ?: throw ModNotBundledException("Mod $mod not bundled in Cobblestone!")
        )
    }
    fun getBundledModVersion(mod: ModContainer)
        = getBundledModVersion(mod.metadata.id)
    fun isBundledModLoaded(mod: String): Boolean {
        if (loader.getModContainer(mod).isEmpty) return false
        return getBundledModVersion(mod).friendlyString.equals(loader.getModContainer(mod).getOrElse {
            return false
        }.metadata.version.friendlyString)
    }
    fun isBundledModLoaded(mod: ModContainer)
        = isBundledModLoaded(mod.metadata.id)
}
