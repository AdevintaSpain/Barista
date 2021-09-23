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
    kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  api("androidx.test.ext:junit:1.1.3")
  api("androidx.compose.ui:ui-test-junit4:1.0.2")

  debugImplementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
  debugImplementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
  debugImplementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
  debugImplementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
  debugImplementation("androidx.activity:activity-compose:1.3.1")
  debugImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
  debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_version"]}")
  debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")

}