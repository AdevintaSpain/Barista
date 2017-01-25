#!/bin/bash

PROJECT_ID="barista-tests"
if [[ $TRAIVS_PULL_REQUEST != "false" ]]; then
  HISTORY_NAME="PR#${TRAIVS_PULL_REQUEST}"
else
  HISTORY_NAME="${TRAIVS_BRANCH}"
fi


set -v

wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-127.0.0-linux-x86_64.tar.gz
tar xf google-cloud-sdk-127.0.0-linux-x86_64.tar.gz
echo "y" | ./google-cloud-sdk/bin/gcloud components update beta
./google-cloud-sdk/bin/gcloud auth activate-service-account --key-file secret.json
./google-cloud-sdk/bin/gcloud beta test android run --type instrumentation --app ./sample/build/outputs/apk/sample-debug.apk --test ./sample/build/outputs/apk/sample-debug-androidTest.apk --device-ids Nexus5 --os-version-ids 22 --locales en --orientations portrait --project ${PROJECT_ID}  --results-history-name "${HISTORY_NAME}"
