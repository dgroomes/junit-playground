plugins {
    java
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

dependencies {
    junitLauncher("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")

    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {

    withType(Test::class.java) {
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
