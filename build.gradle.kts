plugins {
    java
    kotlin("jvm") version "1.3.70"
}

group = "com.github.ryoii"
version = "1.2.1"


repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

val miraiVersion: String = "0.29.1"
val miraiConsoleVersion: String = "0.3.5"
val kotlinVersion: String = "1.3.70"

dependencies {
    compileOnly("net.mamoe:mirai-core-jvm:$miraiVersion")
    compileOnly("net.mamoe:mirai-core-qqandroid-jvm:$miraiVersion")
    compileOnly("net.mamoe:mirai-console:$miraiConsoleVersion")


    compileOnly(kotlin("stdlib-jdk8", kotlinVersion))
    compileOnly(kotlin("stdlib-jdk7", kotlinVersion))
    compileOnly(kotlin("reflect", kotlinVersion))

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}