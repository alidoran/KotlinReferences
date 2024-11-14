import org.gradle.internal.classpath.Instrumented.systemProperty

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
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
    compileSdk = rootProject.extra["compile_target_sdk"] as Int

    defaultConfig {
        applicationId = "android.test"
        minSdk = rootProject.extra["compile_min_sdk"] as Int
        targetSdk = rootProject.extra["compile_target_sdk"] as Int
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
        implementation(project(":commonlibrary"))
        implementation("androidx.appcompat:appcompat:${rootProject.ext["appcompat"]}")
        implementation("com.google.android.material:material:${rootProject.ext["material"]}")
        implementation("androidx.test.ext:junit-ktx:${rootProject.ext["junitktx"]}")
        implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
        implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
        implementation("androidx.test:monitor:${rootProject.ext["test_monitor"]}")
        implementation("androidx.activity:activity-ktx:${rootProject.ext["activity_ktx"]}")
        implementation("androidx.fragment:fragment-ktx:${rootProject.ext["fragment_ktx"]}")
        implementation("androidx.test:runner:${rootProject.ext["test_runner"]}")
        implementation("androidx.databinding:databinding-runtime:${rootProject.ext["databinding_runtime"]}")
        implementation("androidx.core:core-ktx:${rootProject.ext["core_ktx"]}")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.ext["kotlin_version"]}")

        androidTestImplementation(project(":android_test"))
        debugImplementation("androidx.fragment:fragment-testing:${rootProject.ext["fragment_ktx"]}")

        testImplementation("junit:junit:${rootProject.ext["junit"]}")
        testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${rootProject.ext["junit_vintage"]}")
        testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.ext["junit_jupiter"]}")
        testImplementation("androidx.test:core:${rootProject.ext["test_core"]}")
        androidTestImplementation("org.junit.jupiter:junit-jupiter:${rootProject.ext["junit_jupiter"]}")
        androidTestImplementation("androidx.test:rules:1.5.0")

        // Mocking libraries
        testImplementation("io.mockk:mockk:1.13.4")
        testImplementation("androidx.arch.core:core-testing:${rootProject.ext["core_testing"]}")
        testImplementation("org.mockito:mockito-core:${rootProject.ext["mockito_core"]}")
        testImplementation("org.mockito:mockito-inline:3.11.2")
        androidTestImplementation("org.mockito:mockito-android:5.9.0")
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

        // Espresso for UI testing
        androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.ext["espresso"]}")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")

        // Human-readable assertions
        testImplementation("org.hamcrest:hamcrest-all:${rootProject.ext["hamcrest"]}")
        testImplementation("org.amshove.kluent:kluent:${rootProject.ext["kluent"]}")

        // Robolectric for unit tests
        testImplementation("org.robolectric:robolectric:${rootProject.ext["robolectric"]}")

        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

        // Hilt
        implementation("com.google.dagger:hilt-android:2.50")
        ksp("com.google.dagger:hilt-compiler:2.50")
        testImplementation("com.google.dagger:hilt-android-testing:2.50")
        kspTest("com.google.dagger:hilt-compiler:2.50")
        androidTestImplementation("com.google.dagger:hilt-android-testing:2.50")
        kspAndroidTest("com.google.dagger:hilt-compiler:2.50")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")

        testImplementation("org.powermock:powermock-module-junit4:1.7.3")
    }
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}