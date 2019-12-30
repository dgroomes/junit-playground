#!/usr/bin/env bash
# Run the tests using the standalone JUnit console

# Build the soure code (including tests) and install dependencies
./gradlew downloadJunitConsoleLauncher compileTestJava installDist

java -jar junit-platform-console-standalone-*.jar \
  --include-engine junit-jupiter \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath build/classes/java/main \
  --classpath build/classes/java/test \
  --classpath build/install/junit-playground/lib/slf4j-api* \
  --classpath build/install/junit-playground/lib/slf4j-simple*
