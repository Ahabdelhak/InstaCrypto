plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(ConfigData.compileSdkVersion)
    buildToolsVersion(ConfigData.buildToolsVersion)

    defaultConfig {
        minSdkVersion(ConfigData.minSdkVersion)
        targetSdkVersion(ConfigData.targetSdkVersion)


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled =true
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

    buildFeatures {
        // Open view binding
        viewBinding =true
        dataBinding =true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    /*buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion compose_version
                kotlinCompilerVersion ("1.6.21")

    }*/

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

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //core
    implementation(Deps.appCompat)
    implementation(Deps.ktx)
    implementation(Deps.constraintLayout)
    implementation(Deps.kotlin)
    implementation(Deps.materialDesign)

    // Navigation
    implementation(Deps.navFragment)
    implementation(Deps.navUi)

    // JetPack
    implementation(Deps.lifecycleViewmodel)
    implementation(Deps.lifecyclelivedata)
    implementation(Deps.lifecycleRuntime)
    implementation(Deps.fragmentKtx)

    // Hilt dependencies
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)


    //compose
   /* implementation "androidx.compose.ui:ui:$compose_version"
    implementation "androidx.compose.material:material:$compose_version"
    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.activity:activity-compose:1.5.1'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"*/

}