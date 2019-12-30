# junit-playground

Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

Run the tests with `./gradlew test`

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Automate classpath config in the JUnit standalone console by using Gradle (some task like printTestRuntimeClasspath 
  (that's made up)). Tip: write the classpath to a file and leverage https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher-argument-files
* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects. 