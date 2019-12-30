#!/usr/bin/env bash
# Run the tests using the standalone JUnit console

# Build the soure code (including tests), install dependencies, print path to the standalone JUnit Console Launcher,
# and print the test class path to a file.
./gradlew compileTestJava installDist printJunitLauncherPath printTestClassPath

LAUNCHER_PATH_FILE=build/junit-launcher-path.txt
TEST_CLASSPATH_FILE=build/test-classpath.txt

assertFileExists() {
  local file=$1
  if [[ ! -f "$file" ]]; then
    echo >&2 "Expected to find file '$file' but did not"
    exit 1
  fi
}

assertFileExists LAUNCHER_PATH_FILE
assertFileExists TEST_CLASSPATH_FILE

LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")

java -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath @$TEST_CLASSPATH_FILE
