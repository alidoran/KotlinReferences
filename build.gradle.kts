buildscript {
    extra.apply {
        // Projects
        set("compile_target_sdk", 35)
        set("compile_min_sdk", 26)

        // Core
        set("core_ktx", "1.12.0")
        set("fragment_ktx", "1.6.2")
        set("runtime_ktx", "2.8.1")
        set("reflect", "1.8.21")
        set("appcompat", "1.6.1")
        set("constraintlayout", "2.1.4")
        set("material", "1.10.0")
        set("compose", "1.5.0")
        set("lifecycle", "2.6.1")
        set("navigation", "2.7.5")
        set("lifecycle_extensions", "2.2.0")
        set("legacy_support", "1.0.0")
        set("services_maps", "18.1.0")
        set("coroutines", "1.7.3")
        set("apollo", "3.3.2")
        set("play_services_auth", "20.5.0")
        set("lottie", "5.2.0")
        set("rxjava3", "3.1.5")
        set("rxjava2", "2.2.21")
        set("rxandroid", "2.1.1")
        set("pendo", "2.17.0.4509")
        set("firebase_bom", "30.3.1")
        set("constraints", "2.1.4")
        set("vintage_engine", "5.9.0")
        set("stdlib_jdk7", "1.7.20")
        set("support_v4", "1.0.0")
        set("compose_material", "1.4.3")
        set("activity_compose", "1.8.1")
        set("recyclerview", "1.3.2")
        set("material3", "1.1.2")
        set("google_services", "4.3.14")
        set("lifecycle_viewmodel", "1.0.0-alpha03")

        // Test
        set("kluent", "1.68")
        set("junitktx", "1.1.5")
        set("junit4", "1.3.3")
        set("junit", "4.13.2")
        set("junit_vintage", "5.8.2")
        set("junit_jupiter", "5.9.0")
        set("test_core", "1.5.0")
        set("mockk", "1.13.4")
        set("mockito_core", "4.8.0")
        set("mockito_kotlin", "4.1.0")
        set("core_testing", "2.2.0")
        set("espresso", "3.5.1")
        set("test_monitor", "1.5.0")
        set("analytics_ktx", "21.3.0")
        set("analytics", "4.11.3")
        set("test_runner", "1.5.2")
        set("databinding_runtime", "8.2.0")
        set("hamcrest", "1.3")
        set("robolectric", "4.11.1")
        set("activity_ktx", "1.8.2")

        // DI
        set("dagger", "2.50")
        set("hilt", "2.38.1")
        set("hilt_compiler", "1.0.0")
        set("hilt_android", "2.33-beta")

        // Network
        set("retrofit2", "2.9.0")

        // UI
        set("glide", "4.14.2")
        set("paging", "3.2.1")

        // Firebase
        set("firebase_messaging", "23.1.2")

        // Other
        set("jackson", "2.13.3")

        // DB
        set("room", "2.6.0")
        set("kotlin_version", "2.0.21")
        set("agp_version", "8.1.3")
        set("agp_version1", "8.3.0")
    }

    dependencies {
        classpath("com.google.gms:google-services:4.3.14")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("org.jacoco:org.jacoco.core:0.8.10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    }
}

plugins {
    id("com.android.library") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
