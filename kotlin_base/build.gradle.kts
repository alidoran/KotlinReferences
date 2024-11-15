plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = libs.versions.compileTargetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.compileMinSdk.get().toInt()

        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Commented out in the original
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    namespace = "alidoran.kotlin_base"
}

dependencies {
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx.v275)
    implementation(libs.core.ktx)
    implementation(libs.kotlin.stdlib)
    implementation(libs.material)

    implementation(project(":commonlibrary"))

    testImplementation(libs.junit)
}
