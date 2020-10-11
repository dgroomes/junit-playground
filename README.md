# junit-playground

📚 Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

1. Use Java 11
1. Run the program with `./gradlew run --args "Hello World"`
1. Run the tests with `./gradlew test`

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
* DONE Migrate the Java _Early Access_ example to <https://github.com/dgroomes/gradle-playground> because it is really a
  Gradle thing not a JUnit thing.
* Split into sub-projects. In particular, there should be a 'basic' example and a 'junit-standalone' example
