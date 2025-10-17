plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "1.9.23"
    application
}

group = "com.br.tictactoe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.br.tictactoe.MainKt")
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(Ktor.serverCore)
    implementation(Ktor.serverNetty)
    implementation(Ktor.serverWebsockets)
    implementation(Ktor.serializationKotlinxJson)
    implementation(Ktor.serverContentNegotiation)

    implementation(project(SharedModules.shared))

    implementation(Koin.ktor)
    implementation(Koin.ktorLogger)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.br.tictactoe.MainKt"
    }
}