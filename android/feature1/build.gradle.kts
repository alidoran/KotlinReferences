plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

android {
    compileSdk = rootProject.extra["compile_target_sdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["compile_min_sdk"] as Int
        targetSdk = rootProject.extra["compile_target_sdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    namespace = "ir.dorantech.feature"
}

dependencies {
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.navigation:navigation-compose:${rootProject.extra["navigation"]}")
    implementation(project(":android:core:navigation"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
}