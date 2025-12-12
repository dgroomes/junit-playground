# standalone

A build-tool-free (no Gradle/Maven) Java project that is tested with JUnit in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher).


## Instructions

1. Pre-requisite: Java 25
2. Compile the source code
   * ```shell
     ./build.sh
     ```
   * This compiles both the Java source code for the main program, and the Java source code belonging to the JUnit tests.
3. Run the program
   * ```shell
     ./run.sh
     ```
4. Run the JUnit tests
   * ```shell
     ./test.sh
     ```


## Reference

* [`dgroomes/junit-by-hand`](https://github.com/dgroomes/junit-by-hand)
  * This is the original version of this subproject.
