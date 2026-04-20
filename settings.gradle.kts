pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        gradlePluginPortal()
    }
}
plugins {
    id("dev.kikugie.stonecutter") version "0.9.1"
}
stonecutter {
    // The root project is the Tree
    create(rootProject) {
        versions("1.20.1", "1.21.1", "1.21.11", "26.1.2")
        vcsVersion = "26.1.2"
    }
}