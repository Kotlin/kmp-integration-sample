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

include(":app")
include(":shared")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
