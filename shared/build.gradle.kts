plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.br.tictactoe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Log.logbackClassic)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(18)
}