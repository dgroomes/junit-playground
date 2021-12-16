plugins {
    java
}

repositories {
    mavenCentral()
}

// JUnit has a peculiar artifact versioning strategy. For the most part, you can think of JUnit artifacts as starting
// with a 5. For example, JUnit version 5.8.2. In fact, today's JUnit is often branded as JUnit 5. The 5 will be with us
// for many years to come! The peculiar part is the versioning for the JUnit "Platform components". They start with a 1
// but have the same secondary numbers.
//
// See the "Dependency Metadata for JUnit Platform" section in the docs: https://junit.org/junit5/docs/current/user-guide/#dependency-metadata-junit-platform
// See the JUnit 5 release notes page: https://junit.org/junit5/docs/current/release-notes/index.html
//
// You can always figure out the latest version of the JUnit Platform components by replacing the leading 5 with a 1.
// For example, for JUnit 5.8.2, the JUnit Platform components are version 1.8.2.
val junitPlatformVersion = "1.8.2"

dependencies {
    implementation("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")
}

tasks {

    // https://gist.github.com/dgroomes/94f1032ec330ef7f1d9eaf6fa9dff597
    register<Copy>("downloadDependencies") {
        val sourceSet = sourceSets.main.get().runtimeClasspath
        from(sourceSet)
        into("lib/")
    }
}
