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
    compileSdk = rootProject.ext["compile_target_sdk"] as Int

    defaultConfig {
        applicationId = "alidoran.third_party"
        minSdk = rootProject.ext["compile_min_sdk"] as Int
        targetSdk = rootProject.ext["compile_target_sdk"] as Int
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

    testOptions {
        unitTests.all {
//            useJUnitPlatform()
//            systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_method")
//            systemProperty("junit.jupiter.execution.parallel.enabled", "true")
        }
    }

    namespace = "alidoran.third_party"
}

dependencies {
    implementation(project(":commonlibrary"))
    implementation("androidx.core:core-ktx:${rootProject.ext["core_ktx"]}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation("com.google.android.material:material:${rootProject.ext["material"]}")
    implementation("androidx.work:work-runtime-ktx:${rootProject.ext["runtime_ktx"]}")

    implementation("com.google.android.gms:play-services-maps:${rootProject.ext["services_maps"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.ext["kotlin_version"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    testImplementation("junit:junit:${rootProject.ext["junit"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.ext["junitktx"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.ext["espresso"]}")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${rootProject.ext["junit_vintage"]}")
    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.ext["junit_jupiter"]}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${rootProject.ext["reflect"]}")
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.ext["navigation"]}")

    // Dagger
    ksp("com.google.dagger:dagger-compiler:${rootProject.ext["dagger"]}")
    implementation("com.google.dagger:dagger:${rootProject.ext["dagger"]}")
    ksp("com.google.dagger:dagger-compiler:${rootProject.ext["dagger"]}")
    ksp("com.google.dagger:dagger-android-processor:${rootProject.ext["dagger"]}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext["coroutines"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.ext["coroutines"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.ext["lifecycle"]}")

    // Room components
    implementation("androidx.room:room-runtime:${rootProject.ext["room"]}")
    ksp("androidx.room:room-compiler:${rootProject.ext["room"]}")
    androidTestImplementation("androidx.room:room-testing:${rootProject.ext["room"]}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.ext["retrofit2"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.ext["retrofit2"]}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${rootProject.ext["retrofit2"]}")

    // GraphQL converter
    implementation("com.squareup.retrofit2:converter-scalars:${rootProject.ext["retrofit2"]}")

    // Apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.3.2")

    // Mockito
    testImplementation("org.mockito:mockito-core:${rootProject.ext["mockito_core"]}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${rootProject.ext["mockito_kotlin"]}")
    testImplementation("io.mockk:mockk:${rootProject.ext["mockk"]}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${rootProject.ext["firebase_bom"]}"))
    implementation("com.google.android.gms:play-services-auth:${rootProject.ext["play_services_auth"]}")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics-ktx:${rootProject.ext["analytics_ktx"]}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:${rootProject.ext["lifecycle_extensions"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.ext["lifecycle"]}")

    // Lottie player
    implementation("com.airbnb.android:lottie:${rootProject.ext["lottie"]}") {
        exclude(group = "com.android.support")
    }

    // Segment
    implementation("com.segment.analytics.android:analytics:${rootProject.ext["analytics"]}")
    implementation("com.segment.analytics.kotlin:android:1.10.0")

    // CameraX
    val cameraxVersion = "1.2.3"
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    implementation("androidx.camera:camera-video:${cameraxVersion}")
    implementation("androidx.camera:camera-view:${cameraxVersion}")
    implementation("androidx.camera:camera-extensions:${cameraxVersion}")

    // Courier
    implementation("com.github.trycourier:courier-android:2.0.2")

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:${rootProject.ext["rxjava3"]}")
    implementation("io.reactivex.rxjava2:rxjava:${rootProject.ext["rxjava2"]}")
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.ext["rxandroid"]}")
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
