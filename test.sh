#!/usr/bin/env bash
# Run the tests using the standalone JUnit console

# Build the soure code (including tests), install dependencies, and print path to the standalone JUnit Console Launcher
./gradlew compileTestJava installDist printJunitConsoleLauncherPath

LAUNCHER_PATH_FILE=build/junit-standalone-jar-path.txt
if [[ -f "$LAUNCHER_PATH_FILE" ]]; then
  LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")
else
  echo >&2 "Expected to find file '$LAUNCHER_PATH_FILE' but did not"
  exit 1
fi

java -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath build/classes/java/main \
  --classpath build/classes/java/test \
  --classpath build/install/junit-playground/lib/slf4j-api* \
  --classpath build/install/junit-playground/lib/slf4j-simple*
