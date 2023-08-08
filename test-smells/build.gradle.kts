plugins {
    id("java")
}

group = "org.junit"
version = "5.11.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
	testImplementation(projects.platformTests)
	testImplementation(projects.junitJupiterApi)
	testImplementation(projects.junitJupiterEngine)
}

tasks.test {
    useJUnitPlatform()
}