#!/bin/bash

./gradlew build bintrayUpload -PbintrayUser=dryrun-user -PbintrayKey=dryrun-key -PdryRun=true