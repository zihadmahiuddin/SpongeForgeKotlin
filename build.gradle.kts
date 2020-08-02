import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Properties {
    object Versions {
        const val FORGE = "1.12.2-14.23.5.2854"
        const val KOTLIN = "1.3.72"
        const val SPONGE = "7.2.0"
    }
}

buildscript {
    repositories {
        maven {
            url = uri("https://files.minecraftforge.net/maven")
        }
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:3.+")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}


plugins {
    kotlin("jvm") version "1.3.72"
    id("org.spongepowered.plugin") version "0.9.0"
}

apply(plugin = "net.minecraftforge.gradle")

group = "cf.zihad"
version = "1.0"

val JAVA_1_8 = JavaVersion.VERSION_1_8.toString()

val compileJava = tasks.withType<JavaCompile>().first()!!
val compileKotlin = tasks.withType<KotlinCompile>().first()!!
val jar = tasks.withType<Jar>().first()!!

compileJava.options.encoding = "UTF-8"
compileJava.sourceCompatibility = JAVA_1_8
compileJava.targetCompatibility = JAVA_1_8

compileKotlin.kotlinOptions.jvmTarget = JAVA_1_8

jar.baseName = "${project.name}-${version}_S${Properties.Versions.SPONGE}_K${Properties.Versions.KOTLIN}"
jar.version = ""
jar.finalizedBy("reobfJar")

configure<MinecraftExtension> {
    mappings("stable", "39-1.12")
}

val compile = configurations.getByName("compile")
val embed = configurations.create("embed")
val minecraft by configurations

configurations {
    compile.extendsFrom(embed)
}

dependencies {
    minecraft("net.minecraftforge:forge:${Properties.Versions.FORGE}")
    compile(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    embed("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Properties.Versions.KOTLIN}")
    compile("org.spongepowered:spongeapi:${Properties.Versions.SPONGE}")
}

repositories {
    mavenCentral()
    jcenter()
}

jar.from(embed.map { if (it.isDirectory) it as Any else zipTree(it) })
