plugins {
    alias(pluginLibs.plugins.android.library)
    alias(pluginLibs.plugins.kotlin.android)
    alias(pluginLibs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.glance"
    compileSdk = sdk.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = sdk.versions.minSdk.get().toInt()
        targetSdk = sdk.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
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

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            animationsDisabled = true
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.bom)

    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)

    // For AppWidgets support
    implementation(libs.glance.appwidget)
    implementation("androidx.glance:glance-preview:1.1.1")
    implementation("androidx.glance:glance-appwidget-preview:1.1.1")
    // For interop APIs with Material 3
    implementation(libs.glance.material)

    debugImplementation(libs.compose.tooling)
    debugImplementation(testLibs.compose.test.manifest)

    // Unit Tests
    testImplementation(testLibs.glance)
    testImplementation(testLibs.glance.appwidget)
    testImplementation(testLibs.robolectric)
    testImplementation(testLibs.android.junit.ktx)
    testImplementation(testLibs.compose.test.junit)
    // UI Tests
    androidTestImplementation(testLibs.compose.test.junit)
}