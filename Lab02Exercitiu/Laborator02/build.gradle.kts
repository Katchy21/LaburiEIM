// Top-level build file for Laborator02 multi-project workspace

buildscript {
    val kotlin_version by extra("2.0.20")
    val compileSdkVersion by extra(36)
    val buildToolsVersion by extra("34.0.0")
    
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

// Repository configuration is now handled in settings.gradle.kts
// using dependencyResolutionManagement for better Gradle 8+ compatibility

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
