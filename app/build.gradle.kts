import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.protobuf") version "0.9.4"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.yusuferkamozyer.weatherforecastapp"
    compileSdk = 34
    val file=rootProject.file("local.properties")
    val properties= Properties()
    properties.load(FileInputStream(file))

    defaultConfig {
        applicationId = "com.yusuferkamozyer.weatherforecastapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String","openweathermap_api_key",properties.getProperty("apikey_openweathermap"))
        buildConfigField("String","opencage_api_key",properties.getProperty("apikey_opencage"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(libs.androidx.foundation.layout.android)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    //GPS
    implementation("com.google.android.gms:play-services-location:21.1.0")
    // Retrofit
// retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // gson converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")

    val nav_version = "2.8.3"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc02")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc02")

    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.3")

    implementation("androidx.datastore:datastore-preferences:1.1.1")





    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}
kapt {
    correctErrorTypes = true
}

