pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Laborator03"

// Include solutions (Java) project
include(":solutions-app")
project(":solutions-app").projectDir = file("solutions/PhoneDialer/app")

// Include solutions-kotlin project
include(":solutions-kotlin-app")
project(":solutions-kotlin-app").projectDir = file("solutions-kotlin/app")
