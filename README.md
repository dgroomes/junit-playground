# junit-playground

Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

1. Use Java 11
1. Run the program with `./gradlew run --args "Hello World"`
1. Run the tests with `./gradlew test`

### Java Early Access Example

Gradle itself cannot always run on new versions or work-in-progress versions of Java. These versions of Java are
sometimes called _Early Access_ versions. For example, in October 2020 the early access version of Java is Java 16 which
is due out for release next year in March 2021.

> Friendly reminder: use the official OpenJDK site to stay up-to-date on the latest OpenJDK plans, like [Java 16](https://openjdk.java.net/projects/jdk/16/spec/).

Fortunately, Gradle does support compiling Java source for a so-called _Early Access_ version of Java, like Java 16,
even if Gradle itself cannot run on that version of Java! Gradle can do this because it can _fork_ a process to execute
the `compileJava` task with **a different JDK than the JDK used to execute Gradle itself**. Similarly, Gradle can be
configured to use alternative JDKs to execute *execution* tasks like `run` and `test` (the `run` task is provided by the
[application plugin](https://docs.gradle.org/current/userguide/application_plugin.html)).

The [instructions](#instructions) to compile and run with Java 16 (instead of the version of Java executing Gradle)
differ in this way:

1. Set the environment variable `JAVA_16_HOME` to the path of a JDK 16 installation on your computer
1. Run the program with `./gradlew -PTARGET_JAVA_16 run --args "Hello World"`
1. Run the tests with `./gradlew -PTARGET_JAVA_16 test`

### Standalone JUnit Console Launcher Example

An alternative to using Gradle to run the tests is to use the standalone JUnit Console Launcher <https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher>.
Normally, you would want to use Gradle itself to launch and run the test suite, but in some cases you might not have
Gradle, cannot use Gradle, or would prefer to de-couple the build from the execution for some other reason. 

An example use case for the Standalone JUnit Console Launcher is for a project where you want to *build the project 
once* but execute the tests *many* times. In fact, you might want to de-couple the build and the test execution 
entirely. For example, you may have a functional test suite that executes a large collection of tests against a remote
API. The test suite itself may be a large project that takes minutes to build and you might want to execute it every
night or every hour against the remote API. But while the remote API may be changing rapidly your test suite might be 
constant. So, why build it every time? With the Standalone JUnit Console Launcher, you have a way to execute tests 
without a build process!    

The [instructions](#instructions) to compile and run using the Standalone Launcher differ in this way:

1. Build the program and the tests with `./gradlew compileTestJava`
1. Build references to the JUnit launcher and the test classpath  with `./gradlew printJunitLauncherPath printTestClassPath`
1. Run the tests with `./test.sh`. It's fast. It's not building anything!
1. Run the tests again with `./test.sh`. Wow, Java projects can be fast can't they?
1. Run the tests a third time with `./test.sh`. Isn't it freeing to de-couple the test execution from the build? 


## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects.
* Migrate the Java _Early Access_ example to <https://github.com/dgroomes/gradle-playground> because it is really a
  Gradle thing not a JUnit thing.
* Split into sub-projects. In particular, there should be 'basic' example and a 'junit-standalone' example
