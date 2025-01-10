plugins {
    `java-library`
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "dev.espi"
version = "2.10.5"

repositories {
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    api(libs.bstats.bukkit)
    api(libs.night.config.toml)
    api(libs.commons.io)
    api(libs.commons.lang3)
    api(libs.json.simple)
    compileOnly(libs.paper.api)
    compileOnly(libs.worldguard.bukkit)
    compileOnly(libs.vaultapi)
    compileOnly(libs.worldedit.bukkit)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.luckperms.api)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    compileJava {
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
}

tasks.shadowJar {
    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }
}