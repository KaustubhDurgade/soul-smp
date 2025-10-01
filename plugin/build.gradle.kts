plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.soulsmp"
version = "0.1.0"

description = "Soul SMP plugin"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "version" to project.version
        )
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
    archiveBaseName.set("soul-smp")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
