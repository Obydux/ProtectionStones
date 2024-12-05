plugins {
    `java-library`
    `maven-publish`
}

group = "dev.espi"
version = "2.10.5"
description = "ProtectionStones"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(libs.bstats.bukkit)
    api(libs.night.config.toml)
    api(libs.commons.io)
    api(libs.commons.lang3)
    api(libs.json.simple)
    compileOnly(libs.spigot.api)
    compileOnly(libs.worldguard.bukkit)
    compileOnly(libs.vaultapi)
    compileOnly(libs.worldedit.bukkit)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.luckperms.api)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
    withJavadocJar()
}

tasks {
    compileJava {
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}