import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
}

group = "me.rivera"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.google.com")
    }
}

dependencies {
    implementation("android.arch.persistence.room:runtime:1.1.1")
    testImplementation(kotlin("test-junit"))
    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
    // Kotlin
    //implementation ("android.arch.persistence.room:runtime:1.1.0")
    //annotationProcessor ("android.arch.persistence.room:compiler:1.1.0")

    // Gson
    implementation ("com.google.code.gson:gson:2.8.0")

}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}