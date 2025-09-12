plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "memory.card.navigation"
    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_23
        targetCompatibility = JavaVersion.VERSION_23
    }
    kotlin {
        jvmToolchain(23)
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation3)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.coil)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

    implementation(project(":core:domain"))
}