buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.2.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

task<Delete>("clean") {
  setDelete(rootProject.buildDir)
}