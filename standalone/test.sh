#!/usr/bin/env bash
# Run the JUnit tests. JUnit 6 uses subcommands - use 'execute' to run tests.

java -jar lib/junit-platform-console-standalone.jar \
  execute \
  --config=junit.platform.output.capture.stdout=true \
  --config=junit.platform.output.capture.stderr=true \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath out
