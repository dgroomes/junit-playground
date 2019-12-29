# junit-playground

Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

Run the tests with `gradle test`

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Configure a project to use JUnit via the standalone console
* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects. 