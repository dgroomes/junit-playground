# util

The `util/` project is to help download the "JUnit standalone console launcher" jar file which is needed by the
`standalone/` project.

## Instructions

Follow the below instructions to download and move the jar file.

1. Use Java 17
2. Download the jar:
   * `../gradlew downloadDependencies`
3. Move it to the `standalone/` project:
   * `mv lib/junit-platform-console-standalone-* ../standalone/lib/junit-platform-console-standalone.jar`
