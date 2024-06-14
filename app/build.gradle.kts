plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.james.challenge4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.james.challenge4"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":di"))
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    implementation("androidx.room:room-runtime:2.5.2")
//    ksp("androidx.room:room-compiler:2.5.2")
//    implementation("androidx.room:room-ktx:2.5.2")//using coroutine in room
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0") //convert flow to livedata
//    implementation("androidx.datastore:datastore-preferences:1.1.1")
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("io.github.chochanaresh:filepicker:0.2.5")
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    implementation("io.coil-kt:coil:2.6.0")

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}