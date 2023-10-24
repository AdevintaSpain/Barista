plugins {
  id("com.android.library")
  kotlin("android")
}

apply(from = "../config/android-quality.gradle")

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 21
    targetSdk = 33
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = "com.adevinta.android.barista"

  lint {
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

  testImplementation(libs.testing.mockito.core)
}
