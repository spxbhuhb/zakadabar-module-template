/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
}

val stackVersion by extra { "2020.8.29" }

repositories {
    mavenCentral()
    jcenter()

    if (stackVersion.contains("SNAPSHOT")) {
        mavenLocal()
    }

    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/spxbhuhb/zakadabar-stack")
        metadataSources {
            gradleMetadata()
        }
        credentials {
            username = properties["github.user"].toString()
            password = properties["github.key"].toString()
        }
    }
}

group = "hu.simplexion.zakadabar"
version = "2020.8.29"

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {

    jvm()

    js {
        nodejs()
    }

    sourceSets["commonMain"].dependencies {
        api("hu.simplexion.zakadabar:zakadabar-stack:$stackVersion")
    }

    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
}