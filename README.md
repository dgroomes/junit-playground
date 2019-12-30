# junit-playground

Learning and exploring JUnit <https://junit.org/junit5/>.

## Instructions

Run the tests using Gradle with `./gradlew test`

Run the tests using the standalone JUnit Console Launcher with `./test.sh`. Normally, you would want to use Gradle 
itself to launch and run the test suite, but in some cases you might not have Gradle or cannot use Gradle. For example,
you might want to use a newer version of Java that Gradle does not yet support. Gradle generally does not get support 
for newer versions of Java until some wait time after it launches. So if you can't wait and you want to run JUnit tests,
this illustrates a working example of the standalone tool.

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Optimize the CI build (Github Actions). The set up Java step is slow (20s), why even do it? Maybe make a base Docker 
  image tailor-made for Gradle/Java projects. 