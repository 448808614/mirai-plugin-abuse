plugins {
    java
    kotlin("jvm") version "1.3.61"
}

group = "com.github.ryoii"
version = "1.2.0"


repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

val miraiVersion: String = "0.25.0"
val miraiConsoleVersion: String = "0.3.2"
val kotlinVersion: String = "1.3.61"

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