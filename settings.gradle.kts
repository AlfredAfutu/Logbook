pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Logbook"
include(":app")
include(":framework-provider")
include(":framework-provider:interfaces")
include(":viewmodel")
include(":data")
include(":data:repository")
include(":domain")
include(":domain:logic")
include(":domain:model")
include(":screen")
