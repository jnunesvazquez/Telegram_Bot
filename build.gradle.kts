plugins {
    kotlin("jvm") version "1.5.0"
    application
}

group = "me.jjson"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation ("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.4")
}

tasks.test {
    useJUnit()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}