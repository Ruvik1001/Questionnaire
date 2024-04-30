plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ruvik.questionnaire"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ruvik.questionnaire"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.retrofit2)

    implementation(project(path = ":core:common"))
    implementation(project(path = ":data"))
    implementation(project(path = ":domain"))

    implementation(project(path = ":features:sign-in"))
    implementation(project(path = ":features:sign-up"))
    implementation(project(path = ":features:forgot_password"))
    implementation(project(path = ":features:my_tests"))
    implementation(project(path = ":features:create_test"))
    implementation(project(path = ":features:find_test"))
    implementation(project(path = ":features:run_test"))
    implementation(project(path = ":features:see_results"))
    implementation(project(path = ":features:find_interesting"))

    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.firebase.common.ktx)
    implementation(libs.junit.ktx)
}
