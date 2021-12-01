plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
}

apply(from = "../config/android-quality.gradle")

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 21
    targetSdk = 31

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments["clearPackageData"] = "true"

    vectorDrawables.useSupportLibrary = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  testOptions {
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
  }
}

dependencies {
  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("androidx.appcompat:appcompat:1.0.2")
  implementation("androidx.recyclerview:recyclerview:1.0.0")
  implementation("com.google.android.material:material:1.2.0")
  implementation("androidx.annotation:annotation:1.0.2")
  implementation("com.github.bumptech.glide:glide:4.10.0")
  implementation("com.google.android.material:material:1.2.0")
  implementation("androidx.core:core-ktx:1.0.1")

  androidTestUtil("androidx.test:orchestrator:1.3.0")
  androidTestImplementation(project(":library"))
  androidTestImplementation("org.assertj:assertj-core:2.9.1")
  androidTestImplementation("com.nhaarman:mockito-kotlin:1.5.0")
  androidTestImplementation("org.mockito:mockito-android:2.28.2")

  testImplementation("junit:junit:4.12")

}