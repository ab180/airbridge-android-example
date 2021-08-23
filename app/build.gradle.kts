plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    buildToolsVersion = "30.0.3"
    compileSdk = 30

    defaultConfig {
        versionCode = 1
        versionName = "1.0.0"

        minSdk = 30
        targetSdk = 30
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation("com.google.android.material:material:1.4.0")
}