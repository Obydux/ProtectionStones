plugins {
    id("java-library")
    id("maven-publish")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "dev.espi"
version = "2.11.0"
description = "A intuitive Minecraft region protection plugin that uses a certain block to protect regions."

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.papermc.io/repository/maven-public/")
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

tasks.javadoc {
    options.encoding = Charsets.UTF_8.name()
}

tasks.processResources {
    filteringCharset = Charsets.UTF_8.name()
    val props = mapOf(
        "version" to project.version,
        "description" to project.description
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

publishing.publications.create<MavenPublication>("maven") {
    artifact(tasks["shadowJar"])
}

tasks.shadowJar {
    minimize()
    archiveFileName.set("${project.name}-${project.version}.jar")
    relocate("com.electronwill.night-config", "dev.espi.protectionstones.shaded.com.electronwill.night-config")
    relocate("org.bstats", "dev.espi.protectionstones.shaded.org.bstats")
}