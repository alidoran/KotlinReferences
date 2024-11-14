plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = rootProject.extra["compile_target_sdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["compile_min_sdk"] as Int
        targetSdk = rootProject.extra["compile_target_sdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    namespace = "alidoran.fundamental"
}

dependencies {
// Local project dependency
    implementation(project(":commonlibrary"))

    // External dependencies from rootProject.ext properties
    implementation("androidx.core:core-ktx:${rootProject.ext["core_ktx"]}")
    implementation("androidx.appcompat:appcompat:${rootProject.ext["appcompat"]}")
    implementation("com.google.android.material:material:${rootProject.ext["material"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.ext["kotlin_version"]}")

    // Testing dependencies
    testImplementation("junit:junit:${rootProject.ext["junit"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.ext["junitktx"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.ext["espresso"]}")

}