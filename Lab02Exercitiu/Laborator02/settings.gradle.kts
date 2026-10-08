pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        // Still needed for some legacy dependencies
    }
}

rootProject.name = "Laborator02"

// Include all 4 app modules: Java and Kotlin versions of both labtasks and solutions
include(":labtasks-app")
project(":labtasks-app").projectDir = file("labtasks/ActivityLifecycleMonitor/app")

include(":labtasks-k-app")
project(":labtasks-k-app").projectDir = file("labtasks.k/ActivityLifecycleMonitor/app")

include(":solutions-app")
project(":solutions-app").projectDir = file("solutions/ActivityLifecycleMonitor/app")

include(":solutions-k-app")
project(":solutions-k-app").projectDir = file("solutions.k/ActivityLifecycleMonitor/app")
