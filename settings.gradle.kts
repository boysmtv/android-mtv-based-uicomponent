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

rootProject.name = "android-mtv-based-uicomponent"
include(":app")
include(":core:core-ui")
include(":theme:theme-ui")
include(":component:component-button")
include(":component:component-input")
include(":component:component-dialog")
include(":component:component-bottom-sheet")
include(":component:component-card")
include(":component:component-badge")
include(":component:component-checkbox")
include(":aggregator")
