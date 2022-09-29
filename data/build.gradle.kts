import Versions.coreTestingVersion
import Versions.coroutines
import Versions.junitVersion
import Versions.mockkVersion
import Versions.testRunnerVersion
import Versions.truthVersion
import Versions.turbineVersion

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(ConfigData.compileSdkVersion)
    buildToolsVersion(ConfigData.buildToolsVersion)

    defaultConfig {
        minSdkVersion(ConfigData.minSdkVersion)
        targetSdkVersion(ConfigData.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":entity"))

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    // Test
    testImplementation("junit:junit:$junitVersion")
    // testImplementation for pure JVM unit tests
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
    // Run Blocking Test
    testImplementation("androidx.arch.core:core-testing:$coreTestingVersion")
    // Truth
    testImplementation("com.google.truth:truth:$truthVersion")
    // For small test - large test annotations
    testImplementation("androidx.test:runner:$testRunnerVersion")
    // Mock objects
    testImplementation("io.mockk:mockk:$mockkVersion")
    // For Flow test
    testImplementation("app.cash.turbine:turbine:$turbineVersion")

}