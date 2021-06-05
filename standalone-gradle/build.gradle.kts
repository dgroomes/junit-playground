plugins {
    java
    application
}

val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html
val junitJupiterVersion = "5.7.2" // releases: https://junit.org/junit5/docs/current/release-notes/index.html
val junitPlatformVersion = "1.7.2"

repositories {
    mavenCentral()
}

/*
 * Declare a custom "junitLauncher" configuration. For reference on how custom configurations work, see https://docs.gradle.org/current/userguide/declaring_dependencies.html
 */
val junitLauncher by configurations.creating

/**
 * Print the path to the standalone JUnit Console Launcher JAR so it can be used later by "test.sh" to execute the JUnit
 * tests. It's great to leverage Gradle for managing dependencies and setting up environment information for a
 * downstream process like "test.sh"
 */
tasks.register("printJunitLauncherPath") {
    doLast {
        configurations.getByName("junitLauncher").resolve().forEach {
            File(buildDir, "junit-launcher-path.txt").writeText(it.toString())
        }
    }
}

/**
 * Support the standalone JUnit Console Launcher by printing the test class path to a file. This task is used in
 * conjunction with 'printJunitLauncherPath'.
 */
tasks.register("printTestClassPath") {
    doLast {
        val classpath = sourceSets.test.get().runtimeClasspath.joinToString(separator = ":")
        File(buildDir, "test-classpath.txt").writeText(classpath)
    }
}

dependencies {
    junitLauncher("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")

    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

application {
    mainClass.set("dgroomes.Main")
}

tasks {

    withType(Test::class.java) {
        useJUnitPlatform()

        testLogging {
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}
