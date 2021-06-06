#!/usr/bin/env bash
# Run the JUnit tests.

java -jar lib/junit-platform-console-standalone.jar \
  --config=junit.platform.output.capture.stdout=true \
  --config=junit.platform.output.capture.stderr=true \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath out
