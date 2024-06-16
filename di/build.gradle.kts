plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.james.challenge4.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYTljMzljNTIxMTcwZTVlNWZjODExOWQ2YTA5MWMyNSIsInN1YiI6IjY1YWZiYjM4ZjhhZWU4MDBjYmIxMjI0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T3SgL6QLpS9GXPjrNvaawQLNZhszIv7OHXj0QPkIr5M\"")
        }
        create("staging") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYTljMzljNTIxMTcwZTVlNWZjODExOWQ2YTA5MWMyNSIsInN1YiI6IjY1YWZiYjM4ZjhhZWU4MDBjYmIxMjI0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T3SgL6QLpS9GXPjrNvaawQLNZhszIv7OHXj0QPkIr5M\"")     }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYTljMzljNTIxMTcwZTVlNWZjODExOWQ2YTA5MWMyNSIsInN1YiI6IjY1YWZiYjM4ZjhhZWU4MDBjYmIxMjI0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T3SgL6QLpS9GXPjrNvaawQLNZhszIv7OHXj0QPkIr5M\"")      }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    implementation("androidx.room:room-runtime:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")//using coroutine in room

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.test:core:1.5.0")
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

}