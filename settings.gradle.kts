rootProject.name = "otusHomework"
include ("hw01-gradle")
include ("hw04-generics")
include ("hw06-annotations")
include ("hw08-gc")
include ("hw10-byteCodes")
include ("hw12-solid")
include ("hw15-structuralPatterns")
include ("hw16-io")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
    }
}
