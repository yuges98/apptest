// app/build.gradle.kts

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.android)
    // If you added Crashlytics plugin, include it here:
    // alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.s23010743.tatyrootsfinal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.s23010743.tatyrootsfinal"
        minSdk = 23
        targetSdk = 35
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
        isCoreLibraryDesugaringEnabled = true
    }

    // THIS IS THE CORRECTED SYNTAX FOR kotlinOptions BLOCK
    // It should be 'kotlinOptions {' directly within the 'android {' block
    // And 'jvmTarget =' is set inside it.
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.firestore)
    implementation(libs.material)
    implementation(libs.appcompat)
    // You had `implementation(libs.material)` twice. Removed one for clarity/correctness.
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.tasks)
    implementation(libs.firebase.auth)
    // If play-services-basement was causing an issue, ensure it's here too:
    // implementation(libs.play.services.basement)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    coreLibraryDesugaring(libs.desugar.jdk.libs)
}