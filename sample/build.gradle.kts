plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
  id("kotlin-android")
}

apply(from = "../config/android-quality.gradle")

android {
  compileSdk = 30

  defaultConfig {
    testInstrumentationRunnerArguments(mapOf())
    minSdk = 21
    targetSdk = 30

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
  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    kotlinCompilerVersion = "1.5.21"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
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
  implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
  implementation("androidx.activity:activity-compose:1.3.1")

  androidTestUtil("androidx.test:orchestrator:1.3.0")
  androidTestImplementation(project(":library"))
  androidTestImplementation(project(":barista-compose"))
  androidTestImplementation("org.assertj:assertj-core:2.9.1")
  androidTestImplementation("com.nhaarman:mockito-kotlin:1.5.0")
  androidTestImplementation("org.mockito:mockito-android:2.28.2")

  testImplementation("junit:junit:4.12")
}