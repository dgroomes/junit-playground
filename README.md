# junit-playground

📚 Learning and exploring JUnit.

> The programmer-friendly testing framework for Java and the JVM
>
> -- <cite>https://junit.org/</cite>


## Overview

This repository illustrates different concepts, patterns and examples via standalone subprojects. Each subproject is
completely independent of the others and do not depend on the root project. This _standalone subproject constraint_
forces the subprojects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.


### `standalone/`

A build-tool-free (no Gradle/Maven) Java project that is tested with JUnit in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher).

See the README in [standalone/](standalone/).


### `standalone-gradle/`

In a Gradle project, build an escape hatch to run the test suite in a standalone way using the [JUnit Console Launcher](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher)
instead of Gradle.

See the README in [standalone-gradle/](standalone-gradle/).


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

- [ ] SKIP Optimize the CI build (GitHub Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker
  image tailor-made for Gradle/Java projects.
- [x] DONE Migrate the Java _Early Access_ example to <https://github.com/dgroomes/gradle-playground> because it is really a
  Gradle thing not a JUnit thing.
- [x] DONE Split into sub-projects. In particular, there should be a 'basic' example and a 'junit-standalone' example
- [x] DONE Migrate to Gradle's Kotlin DSL
- [x] DONE Is is it possible to use the Gradle application or distribution plugins to create the standalone escape hatch? Would this
  be more idiomatic? Instead of building a file of references to classes by hand, I think this would be better.
