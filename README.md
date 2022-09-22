# InstaCrypto
<img src="https://i.imgur.com/M5Gkifx.png" width="80">
🎯 InstaCrypto consider a base project with Kotlin, MVVM applying clean architecture, alot of modern android stack. the aim of this project is to showcase the latest trends in android development by utilizing the best practices, libraries, and tools to develop a fully-fledged android application.

:point_right: Clean Architecture:
-----------------
<div align="center">
<img src="https://user-images.githubusercontent.com/20733292/191065631-db93241e-db85-4b52-a247-44abd7825be8.png">
</div>


:point_right: What's InstaCrypto App Achieve ? :
-----------------
- Android Architecture Components.
- Separation of concerns.
- Test-ability.
- Loose Coupling.
- Applying SOLID principles.
- Secure app from reverse engineering and protection against security threats.
- Using better dependency management Using buildSrc + Kotlin DSL.

:point_right: Tech Stack :
-----------------
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) - Official Kotlin's tooling for performing asynchronous work.
- [MVVM Architecture](https://developer.android.com/jetpack/guide) - Official recommended architecture for building robust, production-quality apps.
- [Navigation component](https://developer.android.com/guide/navigation?gclid=CjwKCAjwpqCZBhAbEiwAa7pXeZjk0QE0wCj3xe9GKngJ9UurROkHznEj2I_mT6hT1dmTUm95WmVONBoCeQ8QAvD_BwE&gclsrc=aw.ds) - navigation graph for navigating and replacing screens/fragments
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers build state-of-the-art applications.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - DataStore is a data storage solution that stores key-value pairs or typed objects with [protocol buffers](https://developers.google.com/protocol-buffers).
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Retrofit](https://github.com/square/retrofit) - A library for building REST API clients.
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) - A multiplatform Kotlin serialization library.
- [Detekt](https://github.com/detekt/detekt) - A static code analysis library for Kotlin.
- [Ktlint](https://github.com/pinterest/ktlint) - A library for formatting Kotlin code according to official guidelines.
- [Testing](https://developer.android.com/training/testing) - The app is currently covered with unit tests and instrumentation tests.
  - [JUnit](https://junit.org/junit5) - JUnit is a unit testing framework for the Java programming language.
  - [Truth](https://github.com/google/truth) - Truth is a library providing fluent assertions for Java and Android.
  - [MockK](https://github.com/mockk/mockk) - MockK is a mocking library for Kotlin.
  - [Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) - A library for testing Kotlin coroutines.
  - [Turbine](https://github.com/cashapp/turbine) - A testing library for Kotlin Flows.
  - [Dagger Hilt Test](https://developer.android.com/training/dependency-injection/hilt-testing) - A testing library for modifying the Dagger bindings in instrumented tests.
- [Gradle's Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Gradle’s Kotlin DSL is an alternative syntax to the Groovy DSL with an enhanced editing experience.
- [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources) - A special module within the project to manage dependencies.

For more information about used dependencies, see [this](/buildSrc/src/main/java/Dependencies.kt) file.



:point_right: TO DO:
-----------
- [X] Apply UseCases.
- [X] Apply buildSrc + Kotlin DSL for better dependency management.
- [ ] Use Coil for loading images.
- [ ] Add some unit tests.
- [ ] Apply ktlint for checking code style.
- [ ] Apply CI|CD and automate some tasks using github actions.
- [ ] Use git hooks to automate code checking and styling before any new commit.
- [ ] Handle Different Build Variants.
- [ ] Introduce mapper pattern to isolate the logic and making it easier to test and reuse the conversion logic in other classes if necessary.
- [ ] Use JetPack compose for UI .
- [ ] Use Data Store as a local DB for Favourite feature.
- [ ] Use EncryptedSharedPreferences for securing user data.
- [ ] Secure app from reverse engineering.
- [ ] Apply SafetyNet for protection against security threats.
- [ ] Apply ssl certificate concept.
- [ ] Use Ktor as network client instead of Retrofit & OKHTTP.
