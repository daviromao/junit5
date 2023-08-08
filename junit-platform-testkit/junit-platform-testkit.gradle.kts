plugins {
	id("junitbuild.java-library-conventions")
}

description = "JUnit Platform Test Kit"

dependencies {
	api(platform(projects.junitBom))
	api(libs.assertj)
	api(libs.opentest4j)
	api(projects.junitPlatformLauncher)
	implementation("org.ow2.asm:asm-tree:9.5")
	implementation("org.ow2.asm:asm:9.5")

	compileOnlyApi(libs.apiguardian)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
}
