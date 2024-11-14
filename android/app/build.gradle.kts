plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

android {
    compileSdk = rootProject.ext["compile_target_sdk"] as Int

    defaultConfig {
        applicationId = "alidoran.android"
        minSdk = rootProject.ext["compile_min_sdk"] as Int
        targetSdk = rootProject.ext["compile_target_sdk"] as Int
        versionCode = 2
        versionName = "2.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    kotlinOptions {
        jvmTarget = "17"
        allWarningsAsErrors = false
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    namespace = "alidoran.android"

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":commonlibrary"))
    implementation("androidx.core:core-ktx:${rootProject.ext["core_ktx"]}")
    implementation("androidx.fragment:fragment-ktx:${rootProject.ext["fragment_ktx"]}")
    implementation("com.google.android.material:material:${rootProject.ext["material"]}")
    implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.legacy:legacy-support-v4:${rootProject.ext["support_v4"]}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${rootProject.ext["firebase_bom"]}"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.ext["navigation"]}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.ext["navigation"]}")
    implementation("androidx.navigation:navigation-compose:${rootProject.ext["navigation"]}")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.ext["lifecycle"]}")
    implementation("androidx.activity:activity-compose:${rootProject.ext["activity_compose"]}")

    implementation("androidx.appcompat:appcompat:${rootProject.ext["appcompat"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.ext["kotlin_version"]}")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.biometric:biometric-ktx:1.2.0-alpha05")
    implementation("androidx.activity:activity:1.8.0")

    // Test
    testImplementation("androidx.test.ext:junit-ktx:${rootProject.ext["junitktx"]}")
    implementation("androidx.test:core-ktx:${rootProject.ext["test_core"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.ext["junitktx"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.ext["espresso"]}")

    // Room
    implementation("androidx.room:room-ktx:${rootProject.ext["room"]}")
    androidTestImplementation("androidx.room:room-testing:${rootProject.ext["room"]}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext["coroutines"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.ext["coroutines"]}")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:${rootProject.ext["paging"]}")
    implementation("androidx.recyclerview:recyclerview:${rootProject.ext["recyclerview"]}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.ext["retrofit2"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.ext["retrofit2"]}")

    // Glide
    implementation("com.github.bumptech.glide:glide:${rootProject.ext["glide"]}")

    // ViewModel (Lifecycle)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.ext["lifecycle"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.ext["lifecycle"]}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${rootProject.ext["lifecycle"]}")
    implementation("androidx.lifecycle:lifecycle-process:${rootProject.ext["lifecycle"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${rootProject.ext["lifecycle"]}")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("cloud.romhost.timetimberlib:timetimberlib:1.11")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("androidx.webkit:webkit:1.8.0")

    implementation(project(":android:feature1"))
    implementation(project(":android:feature2"))
    implementation(project(":android:core:navigation"))

    // Lottie player
    implementation("com.airbnb.android:lottie:${rootProject.ext["lottie"]}") {
        exclude(group = "com.android.support")
    }
}
