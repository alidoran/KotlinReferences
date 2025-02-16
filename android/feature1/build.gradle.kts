plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

android {
    compileSdk = libs.versions.compileTargetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.compileMinSdk.get().toInt()
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
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.androidx.compose.bom))

    implementation(project(":android:core:navigation"))
}