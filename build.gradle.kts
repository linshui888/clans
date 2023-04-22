@file:Suppress("UnstableApiUsage")

/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("pro.java-conventions")
    id("pro.spigot-conventions")
    id("pro.placeholderapi-conventions")
    id("pro.jitpack-conventions")
    id("pro.panther-conventions")
    id("pro.dynmap-conventions")
    id("pro.codestyle-conventions")
    id("pro.publish-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation(project(":api"))
    implementation("com.github.the-h-team:Enterprise:1.5")
    implementation("com.github.the-h-team:panther-placeholders:${findProperty("pantherVersion")}")
    implementation("com.github.the-h-team.Labyrinth:labyrinth-gui:1.8.3")
    implementation("com.github.the-h-team.Labyrinth:labyrinth-regions:1.8.3")
    implementation("com.github.the-h-team:panther-paste:${findProperty("pantherVersion")}")
    implementation("com.github.the-h-team.Labyrinth:labyrinth-common:1.8.3")
    implementation("com.github.the-h-team.Labyrinth:labyrinth-skulls:1.8.3")
}

tasks.withType<ProcessResources> {
    // Include all resources...
    filesMatching("plugin.yml") {
        // but only expand properties for the plugin.yml
        expand(project.properties)
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("${rootProject.name}-plugin-${project.version}.jar")
    archiveClassifier.set("plugin")
    dependencies {
        include(project(":api"))
    }
}