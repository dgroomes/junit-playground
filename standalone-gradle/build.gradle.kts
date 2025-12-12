plugins {
    java
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

/*
 * Declare a custom "junitLauncher" configuration. For reference on how custom configurations work, see https://docs.gradle.org/current/userguide/declaring_dependencies.html
 */
val junitLauncher by configurations.creating

dependencies {
    // The JUnit BOM (Bill of Materials) manages versions for all JUnit artifacts. JUnit 6 uses unified versioning
    // where Platform, Jupiter, and Vintage all share the same version number.
    implementation(platform(libs.junit.bom))
    testImplementation(platform(libs.junit.bom))

    junitLauncher(libs.junit.platform.console.standalone)

    implementation(libs.slf4j.api)
    implementation(libs.slf4j.simple)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
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
        into(layout.buildDirectory.dir("install/lib"))
    }

    register("installLauncher", Copy::class.java) {
        from(configurations.getByName("junitLauncher"))
        into(layout.buildDirectory.dir("install/bin"))
    }
}
