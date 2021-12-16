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

Build and run the application code, test code and escape hatch with these instructions:

1. Build and install the program and the tests:
   * `./gradlew installLibs`
1. Install the standalone JUnit launcher:
   * `./gradlew installLauncher`
1. Run the tests:
   * `./test.sh`
   * It's fast. It's not building anything!
1. Run the tests again:
   * `./test.sh`
   * Wow, Java projects can be fast can't they? 🚀
1. Run the tests a third time:
   * `./test.sh`
   * Isn't it freeing to de-couple the test execution from the build?

## Reference

* [JUnit 5 Docs: *Configuration Parameters*](https://junit.org/junit5/docs/current/user-guide/#running-tests-config-params)
