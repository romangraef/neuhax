plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    `maven-publish`
    id("org.cadixdev.licenser") version "0.6.1"
    signing
}

group = "moe.nea"
version = "1.0.3"

// Toolchains:
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

sourceSets.main {
    output.setResourcesDir(file("$buildDir/classes/kotlin/main"))
}

// Dependencies:

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io/") {
        content {
            includeGroupByRegex("(com|io)\\.github\\..+")
        }
    }
    maven("https://repo.spongepowered.org/maven/")
    maven("https://repo.polyfrost.cc/releases")
    // If you don't want to log in with your real minecraft account, remove this line
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val runtimeMod by configurations.creating {
    isTransitive = false
    isVisible = false
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    shadowImpl("org.spongepowered:mixin:0.7.11-SNAPSHOT") {
        isTransitive = false
    }
    compileOnly("cc.polyfrost:oneconfig-1.8.9-forge:0.1.0-alpha+") // Should not be included in jar
    shadowImpl("cc.polyfrost:oneconfig-wrapper-launchwrapper:1.0.0-beta+") // Should be included in jar
    annotationProcessor("org.spongepowered:mixin:0.8.4-SNAPSHOT")
    // If you don't want to log in with your real minecraft account, remove this line
    runtimeOnly("me.djtheredstoner:DevAuth-forge-legacy:1.1.0")

    modCompileOnly(runtimeMod("com.github.notenoughupdates:notenoughupdates:4a9e1c4:all")!!)
}

// Minecraft configuration:
loom {
    log4jConfigs.from(file("log4j2.xml"))
    launchConfigs {
        "client" {
            // If you don't want mixins, remove these lines
            property("mixin.debug", "true")
            property("asmhelper.verbose", "true")
            arg("--tweakClass", "cc.polyfrost.oneconfig.loader.stage0.LaunchWrapperTweaker")
            arg("--mixin", "mixins.neuhax.json")
            val modFiles = runtimeMod.files
            arg("--mods", modFiles.joinToString(",") { it.relativeTo(file("run")).path })
        }
    }
    runConfigs {
        delete("server")
    }
    forge {
        pack200Provider.set(dev.architectury.pack200.java.Pack200Adapter())
        // If you don't want mixins, remove this lines
        mixinConfig("mixins.neuhax.json")
    }
    // If you don't want mixins, remove these lines
    @Suppress("UnstableApiUsage")
    mixin {
        defaultRefmapName.set("mixins.neuhax.refmap.json")
    }
}


license {
    header(project.file("HEADER.txt"))
    properties {
        set("name", "Linnea Gräf")
        set("year", 2023)
    }
    skipExistingHeaders(true)
}

// Tasks:

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

tasks.withType(Jar::class) {
    archiveBaseName.set("sky.nea.moe")
    manifest.attributes.run {
        this["FMLCorePluginContainsFMLMod"] = "true"
        this["ForceLoadAsMod"] = "true"

        // If you don't want mixins, remove these lines
        this["TweakClass"] = "cc.polyfrost.oneconfig.loader.stage0.LaunchWrapperTweaker"
        this["MixinConfigs"] = "mixins.neuhax.json"
    }
}


val remapJar by tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
    archiveClassifier.set("thicc")
    from(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks.shadowJar {
    archiveClassifier.set("all-dev")
    configurations = listOf(shadowImpl)

    // If you want to include other dependencies and shadow them, you can relocate them in here
    fun relocate(name: String) = relocate(name, "nea.moe.sky.deps.$name")
}

tasks.assemble.get().dependsOn(tasks.remapJar)

tasks.processResources {
    inputs.property("version", version)
    filesMatching("mcmod.info") {
        expand("version" to version)
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks.remapJar) {
                classifier = "thicc"
            }
            artifact(tasks.jar) {
                classifier = ""
            }
            pom {
                name.set(project.name)
                description.set("Quality of Life Addons of dubious legality for NotEnoughUpdates")
                licenses {
                    license {
                        name.set("GPL-3.0-or-later")
                    }
                }
                developers {
                    developer {
                        name.set("Linnea Gräf")
                    }
                }
                scm {
                    url.set("https://git.nea.moe/nea/neuhax")
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}

