rootProject.name = "SimpleLogin"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":androidApp")
include(":shared")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":shared")
