plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.qrelcome"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.qrelcome"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //ML Kit Android libraries
    implementation("com.google.mlkit:barcode-scanning:17.2.0")
    // CameraX core library
    implementation("androidx.camera:camera-core:1.3.1")
    // CameraX Camera2 implementation
    implementation("androidx.camera:camera-camera2:1.3.1")
    // CameraX Lifecycle library
    implementation("androidx.camera:camera-lifecycle:1.3.1")
    // CameraX View implementation
    implementation("androidx.camera:camera-view:1.3.1")
    //Zxing library
    implementation("com.google.zxing:core:3.4.1")
    //implement gson
    implementation("com.google.code.gson:gson:2.8.8")

    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-firestore")
}