#!/usr/bin/env bash
# Run the tests using the standalone JUnit console.
# Requires that the project is already built and targeting Java 14.

set -eu

./gradlew -PTARGET_JAVA_14 printJunitLauncherPath printTestClassPath

LAUNCHER_PATH_FILE=build/junit-launcher-path.txt
TEST_CLASSPATH_FILE=build/test-classpath.txt
JAVA_14_BIN="$JAVA_14_HOME/bin/java"

assertFileExists() {
  local file=$1
  if [[ ! -f "$file" ]]; then
    echo >&2 "Expected to find file '$file' but did not"
    exit 1
  fi
}

assertFileExists "$LAUNCHER_PATH_FILE"
assertFileExists "$TEST_CLASSPATH_FILE"
assertFileExists "$JAVA_14_BIN"

LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")

"$JAVA_14_BIN" -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath @$TEST_CLASSPATH_FILE
