plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "memory.card.main.data"
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
}

dependencies {
    implementation(libs.bundles.hilt)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines)

    ksp(libs.hilt.compiler)

    implementation(project(":core:data"))
    implementation(project(":feature:card:domain"))
}