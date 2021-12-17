# gradle-standalone

In a Gradle project, build an escape hatch to run the test suite in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher)
instead of Gradle.

## Why?

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

This pattern reminds me of and is vaguely similar to the [`eject`](https://create-react-app.dev/docs/available-scripts/#npm-run-eject)
command in Create React App. The documentation describes the eject command like this:

> If you aren’t satisfied with the build tool and configuration choices, you can eject at any time. This command will
> remove the single build dependency from your project.

While this Gradle project and a Create React App are quite different, the spirit of these "ejector patterns" is the same:
escaping from the constraints of a software build tool after getting the initial benefit from it.

## Instructions

Follow the below instructions to build and test the application in a traditional style. 

1. Use Java 17  
2. Build the program and run the tests
   * `./gradlew test`
   * Gradle makes it as easy as that!
   * The command took 7.6 seconds for me
3. Run the tests again
   * `./gradlew test`
   * It will be really fast because of Gradle's smarts. Gradle knows that it shouldn't execute the tests because none of
     the program code changed since last running the tests. The Gradle daemon is also likely being used which saves
     precious time on subsequent invocations of Gradle commands after the very first. Gradle itself is a large JVM
     program and a Gradle project takes time to "analyze", and the daemon helps with this.
   * The command took 1.7 seconds for me
4. Force run the tests again
   * `./gradlew cleanTest test`
   * It will be a bit slower than the previous step, but still pretty good thanks to the daemon. 
   * The command took 2.9 seconds for me
5. Try it again without the Gradle daemon
   * `./gradlew --no-daemon cleanTest test`
   * It should be about as slow as the original build step because the daemon wasn't there to help.
   * The command took 8.6 seconds for me

Now, follow these instructions to create and use a "Gradle escape hatch" and run the tests in a standalone style.

1. Build and install the program and the tests:
   * `./gradlew installLibs` 
   * The command took 7.6 seconds for me
1. Install the standalone JUnit launcher:
   * `./gradlew installLauncher`
   * The command took 1.6 seconds for me
1. Run the tests without Gradle:
   * `./test.sh`
   * It's fast. It's not building anything!
   * On the other hand, it took more steps to get here than the traditional style.
   * The command took 1.4 seconds for me
1. Run the tests again:
   * `./test.sh`
   * Wow, Java projects can be fast can't they? 🚀
   * The command took 1.2 seconds for me
1. Run the tests a third time:
   * `./test.sh`
   * Isn't it freeing to de-couple the test execution from the build?
   * The command took 1.2 seconds for me

## Reference

* [JUnit 5 Docs: *Configuration Parameters*](https://junit.org/junit5/docs/current/user-guide/#running-tests-config-params)
