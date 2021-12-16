# standalone

A build-tool-free (no Gradle/Maven) Java project that is tested with JUnit in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher).

## Instructions

1. Use Java 17
1. Compile the source code
   * `./build.sh`
   * Note: This compiles both the Java source code for the main program, and the Java source code belonging to the
     JUnit tests
1. Run the program:
   * `./run.sh`
1. Run the JUnit tests:
   * `./test.sh`

## Reference

* [`dgroomes/junit-by-hand`](https://github.com/dgroomes/junit-by-hand)
  * This is the original version of this sub-project.
