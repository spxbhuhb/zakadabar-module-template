/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    signing
    `maven-publish`
}

group = "hu.simplexion.zakadabar"
version = "2020.8.31"

val isSnapshot = version.toString().contains("SNAPSHOT")

val stackVersion by extra { "2020.8.31" }

repositories {
    mavenCentral()
    jcenter()

    if (stackVersion.contains("SNAPSHOT")) {
        mavenLocal()
    }

    fun gps(project: String) {
        maven {
            name = "gps-$project"
            url = uri("https://maven.pkg.github.com/$project")
            metadataSources {
                gradleMetadata()
            }
            credentials {
                username = (properties["github.user"] ?: System.getenv("USERNAME")).toString()
                password = (properties["github.key"] ?: System.getenv("TOKEN")).toString()
            }
        }
    }

    gps("spxbhuhb/zakadabar-stack")
}

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


// -------------------------------------------------------------
// Signing and publishing
// -------------------------------------------------------------

if (! isSnapshot) { // Signing Gradle Module Metadata is not supported for snapshot dependencies.
    signing {
        useGpgCmd()
        sign(publishing.publications)
    }
}

publishing {

    val path = "template/template" // github owner/project name, like spxbhuhb/zakadabar-samples

    repositories {
        if (isSnapshot) {
            mavenLocal()
        } else {
            maven {
                name = "gps"
                url = uri("https://maven.pkg.github.com/$path")
                credentials {
                    username = (properties["github.user"] ?: System.getenv("USERNAME")).toString()
                    password = (properties["github.key"] ?: System.getenv("TOKEN")).toString()
                }
            }
        }
    }

    publications.withType<MavenPublication>().all {

        pom {
            description.set("Template")
            name.set("Template")
            url.set("https://github.com/$path")
            scm {
                url.set("https://github.com/$path")
                connection.set("scm:git:git://github.com/$path.git")
                developerConnection.set("scm:git:ssh://git@github.com/$path.git")
            }
            licenses {
                license {
                    name.set("template")
                    url.set("template")
                    comments.set("template")
                }
            }
            developers {
                developer {
                    id.set("template")
                    name.set("template")
                    url.set("template")
                    organization.set("template")
                    organizationUrl.set("template")
                }
            }
        }
    }
}