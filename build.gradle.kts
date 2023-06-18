import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.openjfx.javafxplugin") version "0.0.14"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    id("io.realm.kotlin") version "1.8.0"
}

group = "gpixel.weather.desktop"
version = "1.0-SNAPSHOT"

val ktor_version = "2.3.1"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "18"
            }
            withJava()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("ch.qos.logback:logback-classic:1.2.3")


                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.realm.kotlin:library-base:1.8.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.instrument", "java.net.http", "jdk.jfr", "jdk.jsobject", "jdk.unsupported", "jdk.unsupported.desktop", "jdk.xml.dom")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "WeatherDesktopApp"
            packageVersion = "1.0.0"

            linux {

            }
        }
    }
}

javafx {
    version = "20.0.1"
    modules = listOf("javafx.controls", "javafx.swing", "javafx.web", "javafx.graphics")
}
