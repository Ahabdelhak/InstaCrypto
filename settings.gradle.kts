dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "InstaCrypto"
include(":app")
include(":feature")
include(":common")
include(":data")
include(":entity")
include(":remote")
include(":domain")