/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}" }
    val navigation by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navComponentVersion}" }
    val serialization by lazy { "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"}
}

/**
 * To define dependencies
 */
object Deps {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val multidex by lazy { "com.android.support:multidex:${Versions.multidex}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt_version}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}" }
    val ktx by lazy { "androidx.core:core-ktx:${Versions.ktx}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }

    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}" }
    val okhttp by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}" }
    val conscrypt by lazy { "org.conscrypt:conscrypt-android:${Versions.conscrypt}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val navFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navComponentVersion}" }
    val navUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navComponentVersion}" }
    val lifecycleViewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_viewmodel}" }
    val lifecyclelivedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata_ktx}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }

}
