plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-android")
    id("com.apollographql.apollo3") version "3.3.2"
    // Firebase
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = libs.versions.compileTargetSdk.get().toInt()

    defaultConfig {
        applicationId = "alidoran.third_party"
        minSdk = libs.versions.compileMinSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    namespace = "alidoran.third_party"
}

dependencies {
    implementation(project(":commonlibrary"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.material)
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.play.services.maps)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testRuntimeOnly(libs.junit.vintage.engine)
    testImplementation(libs.junit.jupiter)
    implementation(libs.kotlin.reflect)
    implementation(libs.androidx.navigation.fragment.ktx)

    // Dagger
    ksp("com.google.dagger:dagger-compiler:${libs.versions.dagger.get()}")
    implementation(libs.dagger)
    ksp("com.google.dagger:dagger-compiler:${libs.versions.dagger.get()}")
    ksp("com.google.dagger:dagger-android-processor:${libs.versions.dagger.get()}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${libs.versions.coroutines.get()}")
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Room components
    implementation(libs.androidx.room.runtime)
    ksp("androidx.room:room-compiler:${libs.versions.room.get()}")
    androidTestImplementation(libs.androidx.room.testing)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)

    // GraphQL converter
    implementation(libs.converter.scalars)

    // Apollo
    implementation(libs.apollo.runtime)

    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation("io.mockk:mockk:${libs.versions.mockk.get()}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${libs.versions.firebaseBom.get()}"))
    implementation(libs.play.services.auth)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v262)

    // Lottie player
    implementation("com.airbnb.android:lottie:${libs.versions.lottie.get()}") {
        exclude(group = "com.android.support")
    }

    // Segment
    implementation(libs.analytics)
    implementation(libs.android)

    // CameraX
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)

    // Courier
    implementation(libs.courier.android)

    // RxJava
    implementation(libs.rxjava)
    implementation(libs.rxjava2.rxjava)
    implementation(libs.rxandroid)
}

apollo {
    service("weather") {
        sourceFolder.set("weather")
        packageName.set("weather")
    }
    service("rocketreserver") {
        sourceFolder.set("com/example/rocketreserver")
        packageName.set("com.example.rocketreserver")
    }
}
