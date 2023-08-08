plugins {
	id("junitbuild.java-library-conventions")
}

description = "JUnit Jupiter (Aggregator)"

dependencies {
	api(platform(projects.junitBom))
	api(projects.junitJupiterApi)
	api(projects.junitJupiterParams)
	implementation("org.ow2.asm:asm:9.5")
	implementation("org.ow2.asm:asm-tree:9.5")

	runtimeOnly(projects.junitJupiterEngine)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
}
