#!/usr/bin/env bash
# Run the tests using the Standalone JUnit Console Launcher.
# Requires that the project is already built

set -eu

if [[ ! -d "build" ]]; then
  echo >&2 "Build the project first"
  exit 1
fi

LAUNCHER_PATH_FILE=build/junit-launcher-path.txt
TEST_CLASSPATH_FILE=build/test-classpath.txt

assertFileExists() {
  local file=$1
  if [[ ! -f "$file" ]]; then
    echo >&2 "Expected to find file '$file' but did not"
    exit 1
  fi
}

assertFileExists "$LAUNCHER_PATH_FILE"
assertFileExists "$TEST_CLASSPATH_FILE"

LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")

java -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --reports-dir=build/test-results/test \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath @$TEST_CLASSPATH_FILE
