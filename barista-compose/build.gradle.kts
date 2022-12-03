plugins {
  id("com.android.library")
  kotlin("android")
}

apply(from = "../config/android-quality.gradle")

ext["PUBLISH_ARTIFACT_ID"] = "barista-compose"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 21
    targetSdk = 31

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments["clearPackageData"] = "true"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  api(libs.androidX.test.junit)
  api(libs.androidX.compose.test.ui.junit4)

  debugImplementation(libs.androidX.compose.ui.ui)
  debugImplementation(libs.androidX.compose.material)
  debugImplementation(libs.androidX.compose.ui.tooling.preview)
  debugImplementation(libs.androidX.compose.ui.tooling.core)
  debugImplementation(libs.androidX.compose.test.ui.manifest)

  debugImplementation(libs.androidX.lifecycle)
  debugImplementation(libs.androidX.activity.compose)

}