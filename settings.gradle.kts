enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
  repositories {
    google()
    mavenCentral()
  }
}

include(":sample")

include(":taggingviewer")
include(":taggingviewer-no-op")
