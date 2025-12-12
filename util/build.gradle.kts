plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.junit.platform.console.standalone)
}

tasks {

    // https://gist.github.com/dgroomes/94f1032ec330ef7f1d9eaf6fa9dff597
    register<Copy>("downloadDependencies") {
        val sourceSet = sourceSets.main.get().runtimeClasspath
        from(sourceSet)
        into("lib/")
    }
}
