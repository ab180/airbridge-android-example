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
        // Airbridge SDK 저장소를 추가합니다.
        // Add the Airbridge SDK repository.
        maven { url = uri("https://sdk-download.airbridge.io/maven") }
    }
}

rootProject.name = "AirbridgeSampleApp"
include(":app")
