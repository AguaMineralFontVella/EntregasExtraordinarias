import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.22"
    application
}

group = "es.sergiop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //Serializacion
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    //Kmongo
    implementation("org.litote.kmongo:kmongo-async:4.9.0")
    implementation("org.litote.kmongo:kmongo-coroutine:4.9.0")

    // Bcrypt
    implementation("com.ToxicBakery.library.bcrypt:bcrypt:1.0.9")

    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    //Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.3")
    implementation("ch.qos.logback:logback-classic:1.4.8")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.17")

    //Cache 4k
    implementation("io.github.reactivecircus.cache4k:cache4k:0.9.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}