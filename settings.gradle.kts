pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Weather"
include(":app")
include(":core:data-source:remote")
include(":core:ui")
include(":feature:home")
include(":core:data-source:local")
include(":core:data")
include(":core:model")
