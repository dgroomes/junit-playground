plugins {
    java
}

val slf4jVersion = "1.7.32" // SLF4J releases: http://www.slf4j.org/news.html

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
val junitJupiterVersion = "5.8.2" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html
val junitPlatformVersion = "1.8.2"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

/*
 * Declare a custom "junitLauncher" configuration. For reference on how custom configurations work, see https://docs.gradle.org/current/userguide/declaring_dependencies.html
 */
val junitLauncher by configurations.creating

dependencies {
    junitLauncher("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")

    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {

    test {
        useJUnitPlatform()

        testLogging {
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }

    /**
     * Build a jar file containing the compiled test classes.
     */
    register("testJar", Jar::class.java) {
        archiveFileName.set("test.jar")
        from(sourceSets["test"].output)
    }

    /**
     * Copy all library files to a directory. These files include the application's main source set (as a .jar file), the
     * application's test source set (as a .jar file), the main dependencies (.jar files) and the test dependencies (.jar files).
     *
     * Partially gleaned from https://github.com/gradle/gradle/blob/e5de9e91f726af15eac246caff489d8a65112e35/subprojects/plugins/src/main/java/org/gradle/api/plugins/ApplicationPlugin.java#L217
     */
    register("installLibs", Copy::class.java) {
        from(configurations.runtimeClasspath)
        from(configurations.testRuntimeClasspath)
        from(project.tasks.named("jar"))
        from(project.tasks.named("testJar"))
        into("${buildDir}/install/lib")
    }

    register("installLauncher", Copy::class.java) {
        from(configurations.getByName("junitLauncher"))
        into("${buildDir}/install/bin")
    }
}
