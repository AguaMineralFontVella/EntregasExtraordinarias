plugins {
    kotlin("jvm") version "1.8.20"
    application
    kotlin("plugin.serialization") version "1.8.20"
}

group = "es.sergiop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // SQLite
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")

    // MyBatis
    implementation("org.mybatis:mybatis:3.5.11")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")

    // Bcrypt
    implementation("com.ToxicBakery.library.bcrypt:bcrypt:1.0.9")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Cache4K
    implementation("io.github.reactivecircus.cache4k:cache4k:0.10.0")


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}