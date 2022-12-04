enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
  repositories {
    google()
    mavenCentral()
  }
}

include(":sample")

include(":barista")
include(":barista-compose")
