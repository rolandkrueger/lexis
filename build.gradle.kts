import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.3.0"
    id("org.jetbrains.dokka") version "0.9.17"
}

group = "org.jlexis"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("io.strikt", "strikt-core", "0.17.0")
    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.3.1")
    testCompile("org.junit.jupiter", "junit-jupiter-engine", "5.3.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputDirectory = "$buildDir/docs/dokka"
        jdkVersion = 8
    }
}