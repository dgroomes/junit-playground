plugins {
    java
}

repositories {
    mavenCentral()
}

val junitJupiterVersion = "5.7.2" // releases: https://junit.org/junit5/docs/current/release-notes/index.html
val junitPlatformVersion = "1.7.2"

dependencies {
    implementation(group = "org.junit.platform", name = "junit-platform-console-standalone", version = junitPlatformVersion)
}

tasks {

    // https://gist.github.com/dgroomes/94f1032ec330ef7f1d9eaf6fa9dff597
    register<Copy>("downloadDependencies") {
        val sourceSet = sourceSets.main.get().runtimeClasspath
        from(sourceSet)
        into("lib/")
    }
}
