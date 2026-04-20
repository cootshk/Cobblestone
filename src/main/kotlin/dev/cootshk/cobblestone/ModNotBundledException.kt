package dev.cootshk.cobblestone

class ModNotBundledException : RuntimeException {
    constructor(): super()
    constructor(msg: String): super(msg)
}