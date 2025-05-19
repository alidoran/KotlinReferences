plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    compileSdk = libs.versions.compileTargetSdk.get().toInt()

    defaultConfig {
        namespace = "alidoran.design_pattern"
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

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_method")
        systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    }

    buildFeatures {
        viewBinding = true
    }

    namespace = "alidoran.design_pattern"
}

dependencies {
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.constraintlayout.core)
    implementation(libs.core.ktx)
    implementation(libs.kotlin.stdlib)
    implementation(libs.material)

    implementation(project(":commonlibrary"))

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.vintage.engine)
}
