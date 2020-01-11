# junit-playground

Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

1. Use Java 13
1. Run the tests with `./gradlew test`

### Standalone JUnit Console Launcher

An alternative to using Gradle to run the tests is to use the standalone JUnit Console Launcher <https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher>.
Normally, you would want to use Gradle itself to launch and run the test suite, but in some cases you might not have
Gradle or cannot use Gradle. 

#### Java 14 Example

An example use case for the Standalone JUnit Console Launcher is for a project using an early-access version of Java, 
like Java 14. Gradle generally does not support early-access versions of Java. So, we can use the Standalone JUnit
Console Launcher to make do.

To illustrate this use case, this project is configured to optionally compile using Java 14. Gradle actually does 
support *compiling* using early-access versions of Java, but it does not support execution tasks (like the `test` task).

Follow these instructions:

1. Set the environment variable `JAVA_14_HOME` to the path of a JDK 14 installation on your computer
1. Compile the project with `./gradlew -PTARGET_JAVA_14 compileJava compileTestJava`
1. Try to run the tests with `./gradlew -PTARGET_JAVA_14 test`. This step will wail fail because it will try to use Java 13 to execute code
   compiled for Java 14. Specifically, it will throw an error with this message:
   > dgroomes/MainTest has been compiled by a more recent version of the Java Runtime (class file version 58.0), this version of the Java Runtime only recognizes class file versions up to 57.0
1. Run the tests with `./test.sh`. This will succeed because it uses the Standalone JUnit Console Launcher and Java 14.


## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects. 