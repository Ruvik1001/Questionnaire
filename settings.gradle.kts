pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Questionnaire"
include(":app")
include(":core")
include(":core:common")
include(":features")
include(":features:sign-in")
include(":data")
include(":domain")
include(":features:sign-up")
include(":features:forgot_password")
include(":features:my_tests")
include(":features:create_test")
include(":features:find_test")
include(":features:run_test")
include(":features:see_results")
include(":features:find_interesting")
