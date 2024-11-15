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
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.room.testing)

    implementation("com.airbnb.android:lottie:${libs.versions.lottie.get()}") {
        exclude(group = "com.android.support")
    }
    implementation(libs.adapter.rxjava2)
    implementation(libs.analytics)
    implementation(libs.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v262)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.apollo.runtime)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation(libs.core.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.courier.android)
    implementation(libs.dagger)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.material)
    implementation(libs.play.services.auth)
    implementation(libs.play.services.maps)
    implementation(libs.retrofit)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.rxjava2.rxjava)
    implementation(platform(libs.firebase.bom))

    implementation(project(":commonlibrary"))

    ksp(libs.dagger.compiler)
    ksp(libs.dagger.processor)
    ksp(libs.room.compiler)
    
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testRuntimeOnly(libs.junit.vintage.engine)
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
