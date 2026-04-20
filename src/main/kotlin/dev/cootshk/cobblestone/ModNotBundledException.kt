package dev.cootshk.cobblestone

open class ModNotBundledException : RuntimeException {
    internal constructor(): super()
    internal constructor(msg: String): super(msg)
}