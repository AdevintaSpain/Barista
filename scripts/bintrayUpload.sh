#!/bin/bash

./gradlew bintrayUpload -PbintrayUser="${BINTRAY_USER}" -PbintrayKey="${BINTRAY_KEY}" -PdryRun=false