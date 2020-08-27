/*
 * Copyright © 2020, Simplexion, Hungary and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
}

val stackVersion by extra { "2020.8.28-SNAPSHOT" }

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
            username = properties["user"].toString()
            password = properties["key"].toString()
        }
    }
}

group = "hu.simplexion.zakadabar.template"
version = "2020.8.27"

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