import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "gpixel.weather.desktop"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
//        jvmToolchain(11)
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
            withJava()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
//                implementation("org.openjfx:javafx-web:20.0.1")
//                implementation("org.openjfx:javafx-controls:20.0.1")
//                implementation("org.openjfx:javafx-graphics:20.0.1")
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
