plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("jacoco")
}

tasks.register<JacocoReport>("jacocoReport") {
    dependsOn("testDebugUnitTest")

    reports {
        csv.required.set(false)
        xml.required.set(false)
        html.required.set(true)
        html.outputLocation.set(file("${buildDir}/coverage-report"))
    }

    val fileFilter =
        listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "android/**/*.*")

    // Include this if you use Kotlin
    val kotlinTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    val javacTree = fileTree("${project.buildDir}/intermediates/javac/debug") {
        exclude(fileFilter)
    }
    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(kotlinTree, javacTree))
    executionData.setFrom(fileTree(project.buildDir) {
        include("jacoco/testDebugUnitTest.exec", "outputs/code-coverage/connected/*coverage.ec")
    })
}

android {
    compileSdk = libs.versions.compileTargetSdk.get().toInt()

    defaultConfig {
        applicationId = "android.test"
        minSdk = libs.versions.compileMinSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        resources {
            excludes += listOf("META-INF/LICENSE.md", "META-INF/LICENSE-notice.md")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    namespace = "android.test"
}

dependencies {
    dependencies {
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.rules)
        androidTestImplementation(libs.hilt.android.testing)
        androidTestImplementation(libs.junit.jupiter)
        androidTestImplementation(libs.mockito.android)
        androidTestImplementation(project(":android_test"))
        debugImplementation(libs.androidx.fragment.testing)
        implementation(libs.androidx.activity.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.constraintlayout.compose)
        implementation(libs.androidx.constraintlayout.core)
        implementation(libs.androidx.databinding.runtime)
        implementation(libs.androidx.fragment.ktx)
        implementation(libs.androidx.junit.ktx)
        implementation(libs.androidx.monitor)
        implementation(libs.androidx.runner)
        implementation(libs.core.ktx)
        implementation(libs.hilt.android)
        implementation(libs.jackson.databind)
        implementation(libs.kotlin.stdlib)
        implementation(libs.material)

        implementation(project(":commonlibrary"))

        ksp(libs.hilt.compiler)
        kspAndroidTest(libs.hilt.compiler)
        kspTest(libs.hilt.compiler)

        testImplementation(libs.androidx.core.ktx)
        testImplementation(libs.androidx.core.testing)
        testImplementation(libs.hamcrest.all)
        testImplementation(libs.hilt.android.testing)
        testImplementation(libs.junit)
        testImplementation(libs.junit.jupiter)
        testImplementation(libs.junit.jupiter.api)
        testImplementation(libs.junit.jupiter.engine)
        testImplementation(libs.kluent)
        testImplementation(libs.kotlinx.coroutines.test)
        testImplementation(libs.mockito.core)
        testImplementation(libs.mockito.inline)
        testImplementation(libs.mockito.kotlin)
        testImplementation(libs.mockk)
        testImplementation(libs.powermock.module.junit4)
        testImplementation(libs.robolectric)

        testRuntimeOnly(libs.junit.vintage.engine)
    }
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}