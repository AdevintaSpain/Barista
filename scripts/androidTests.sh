#!/bin/bash

PROJECT_ID="barista-tests"

set -v

wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-127.0.0-linux-x86_64.tar.gz
tar xf google-cloud-sdk-127.0.0-linux-x86_64.tar.gz
echo "y" | ./google-cloud-sdk/bin/gcloud components update beta
./google-cloud-sdk/bin/gcloud auth activate-service-account --key-file secret.json
./google-cloud-sdk/bin/gcloud beta test android run firebase-test.yml:test-suite --project ${PROJECT_ID}
