plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
}

apply(from = "../config/android-quality.gradle")

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 21
    targetSdk = 33

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
  namespace = "com.adevinta.android.barista.sample"
}

dependencies {
  implementation(libs.androidX.legacy.supportV4)

  implementation(libs.androidX.appCompat)
  implementation(libs.androidX.recyclerView)
  implementation(libs.androidX.material)
  implementation(libs.androidX.annotation)

  implementation(libs.glide)
  implementation(libs.androidX.material)
  implementation(libs.androidX.core)

  androidTestUtil(libs.androidX.test.orchestrator)
  androidTestImplementation(project(":barista"))

  androidTestImplementation(libs.testing.assertJ)
  androidTestImplementation(libs.testing.mockito.kotlin)
  androidTestImplementation(libs.testing.mockito.android)

  testImplementation(libs.testing.jUnit)
  // testImplementation("org.hamcrest:hamcrest:2.2")
  // testImplementation("androidx.test.ext:junit-ktx:1.1.5")
  androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
  //testImplementation("androidx.test:core:1.5.0")
  androidTestImplementation("androidx.test:core:1.5.0")
  //testImplementation("androidx.test:rules:1.5.0")
  androidTestImplementation("androidx.test:rules:1.5.0")
  //testImplementation("androidx.test.ext:truth:1.5.0")
  //androidTestImplementation("androidx.test.ext:truth:1.5.0")
}