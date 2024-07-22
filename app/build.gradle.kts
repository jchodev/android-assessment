import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")

    kotlin("plugin.serialization")
    alias(libs.plugins.compose.compiler)

}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())
val baseUrl = localProperties.getProperty("BASE_URL")

//make junit 5 work
tasks.withType(Test::class.java).configureEach {
    useJUnitPlatform()
}

android {
    namespace = "com.jerry.assessment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jerry.assessment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

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
        buildConfig = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
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

    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //jetpack compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose)
    debugImplementation(libs.compose.ui.tooling.debug)
    //it is used for test UI with jetoack compose
    androidTestImplementation (libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.test.manifest)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.dagger.compiler)

    //timber
    implementation(libs.timber)

    //network
    implementation(libs.bundles.retrofit)

    //junit5
    testImplementation(libs.bundles.junit5.test.implementation)
    testRuntimeOnly(libs.bundles.junit5.test.runtime.only)
    //turbine
    testImplementation(libs.turbine)

    //media3
    implementation(libs.bundles.media3)

    implementation(libs.kotlinx.serialization.json)
}

kapt {
    correctErrorTypes = true
}