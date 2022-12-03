plugins {
  id("com.android.library")
  kotlin("android")
}

apply(from = "../config/android-quality.gradle")

ext["PUBLISH_ARTIFACT_ID"] = "barista"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 30

  defaultConfig {
    minSdk = 19
    targetSdk = 30
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  lintOptions {
    disable.add("InvalidPackage")
  }
}

dependencies {
  api(libs.androidX.test.espresso.core)
  api(libs.androidX.test.espresso.contrib)
  api(libs.androidX.test.uiAutomator)
  api(libs.androidX.test.espresso.intents)
  
  implementation(libs.androidX.annotation)
  implementation(libs.androidX.legacy.support)
  implementation(libs.androidX.vectorDrawable.animated)
  implementation(libs.androidX.recyclerView)
  api(libs.androidX.viewPager2)
  
  implementation(libs.androidX.material)
  
  testImplementation(libs.testing.jUnit)
  testImplementation(libs.testing.jUnitParams)
  testImplementation(libs.testing.assertJ)
  
  testImplementation(libs.testing.mockito)
}
