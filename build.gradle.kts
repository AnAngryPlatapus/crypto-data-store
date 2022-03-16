import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.20-RC"
	id("org.jetbrains.kotlin.plugin.spring") version "1.6.20-RC"//	id("org.springframework.experimental.aot") version "0.11.1"
}


group = "com.sunny"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	maven { url = uri("https://repo.spring.io/release") }
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("com.fasterxml.jackson.core:jackson-core")
	implementation ("com.fasterxml.jackson.core:jackson-annotations")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("org.tensorflow:tensorflow-core-platform:0.4.0")
	implementation("org.apache.commons:commons-math3:3.6.1")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.twitter4j:twitter4j-core:4.0.7")
	runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.74.Final:osx-aarch_64")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.0")
	implementation("com.google.code.gson:gson:2.9.0")
//	implementation("io.ktor:ktor-client-core:1.3.2-1.4-M2")
//	implementation("io.ktor:ktor-client-ios:1.3.2-1.4-M2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
	builder = "paketobuildpacks/builder:tiny"
//	environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

