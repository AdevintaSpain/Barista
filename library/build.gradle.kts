plugins {
  id("com.android.library")
  kotlin("android")
}

apply(from= "../config/android-quality.gradle")

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
  api("androidx.test.ext:junit:1.0.0")
  api("androidx.test.espresso:espresso-core:3.4.0")
  api("androidx.test.espresso:espresso-contrib:3.4.0")
  api("androidx.test.uiautomator:uiautomator:2.2.0")
  api("androidx.test.espresso:espresso-intents:3.4.0")


  implementation("androidx.annotation:annotation:1.0.2")
  implementation("androidx.legacy:legacy-support-core-ui:1.0.0")
  implementation("androidx.vectordrawable:vectordrawable-animated:1.0.0")
  implementation("androidx.recyclerview:recyclerview:1.0.0")
  api("androidx.viewpager2:viewpager2:1.0.0")

  implementation("com.google.android.material:material:1.2.0")

  testImplementation("junit:junit:4.12")
  testImplementation("pl.pragmatists:JUnitParams:1.1.0")
  testImplementation("org.assertj:assertj-core:1.7.0")
  testImplementation("org.mockito:mockito-core:2.28.2")
}
