plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = rootProject.extra["compile_target_sdk"] as Int

    defaultConfig {
        applicationId = "alidoran.design_pattern"
        minSdk = rootProject.extra["compile_min_sdk"] as Int
        targetSdk = rootProject.extra["compile_target_sdk"] as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

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
    implementation(project(":commonlibrary"))
    implementation("androidx.core:core-ktx:${rootProject.ext["core_ktx"]}")
    implementation("androidx.appcompat:appcompat:${rootProject.ext["appcompat"]}")
    implementation("com.google.android.material:material:${rootProject.ext["material"]}")
    implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.ext["kotlin_version"]}")
    testImplementation("junit:junit:${rootProject.ext["junit"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.ext["junitktx"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.ext["espresso"]}")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${rootProject.ext["vintage_engine"]}")
    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.ext["junit_jupiter"]}")

}
