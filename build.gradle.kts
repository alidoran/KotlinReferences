buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.org.jacoco.core)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    id("com.android.library") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose) apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
