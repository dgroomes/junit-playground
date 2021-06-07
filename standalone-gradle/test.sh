#!/usr/bin/env bash
# Run the tests using the Standalone JUnit Console Launcher.
# Requires that the project is already built. See the README for instructions.

set -eu

LIB_DIR=build/install/lib

if [[ ! -d "$LIB_DIR" ]]; then
  echo >&2 "Expected to find the directory '$LIB_DIR' which should contain all .jar files but did not. See the README for instructions."
  exit 1
fi

LAUNCHER_PATH_PREFIX=build/install/bin/junit-platform-console-standalone

if ! ls "$LAUNCHER_PATH_PREFIX"* &> /dev/null; then
  echo >&2 "Expected to find the JUnit standalone launcher file but did not. See the README for instructions."
  exit 1
fi

CLASSPATH_FILE=build/classpath.txt
# Build the classpath file by enumerating the paths to all .jar files
find "$LIB_DIR" | tr '\n' ':' > "$CLASSPATH_FILE"

#LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")
LAUNCHER_PATH=$(ls "$LAUNCHER_PATH_PREFIX"*)

# TODO explain all of these configs
java \
  -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --reports-dir=build/test-results/test \
  --fail-if-no-tests \
  --config=junit.platform.output.capture.stdout=true \
  --config=junit.platform.output.capture.stderr=true \
  --scan-classpath \
  --classpath "@$CLASSPATH_FILE"
