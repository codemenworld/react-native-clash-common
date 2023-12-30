plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

val coroutine = "1.6.3"
val coreKtx = "1.8.0"

dependencies {
    implementation("com.github.codemenworld:react-native-clash-hideapi:37cd4d58de")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine")
    implementation("androidx.core:core-ktx:$coreKtx")
}

repositories {
    mavenCentral()
    google()
    maven("https://maven.kr328.app/releases")
    maven("https://jitpack.io")
}

android {
    ndkVersion = "23.0.7599858"

    compileSdk = 31

    defaultConfig {
        minSdk = 21

        consumerProguardFiles("consumer-rules.pro")

    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.findByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.codemenworld"
            artifactId = "react-native-clash-common"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}