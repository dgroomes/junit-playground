# junit-playground

📚 Learning and exploring JUnit <https://junit.org/junit5/>.

## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:

### `standalone-gradle/`

In a Gradle project, build an escape hatch to run the test suite in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher)
instead of Gradle.

See the README in [standalone-gradle/](standalone-gradle/).

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects.
* DONE Migrate the Java _Early Access_ example to <https://github.com/dgroomes/gradle-playground> because it is really a
  Gradle thing not a JUnit thing.
* DONE Split into sub-projects. In particular, there should be a 'basic' example and a 'junit-standalone' example
* DONE Migrate to Gradle's Kotlin DSL
* Remove usage of deprecated Gradle features. Use '--warning-mode all' with Gradle to show the individual deprecation
  warnings
