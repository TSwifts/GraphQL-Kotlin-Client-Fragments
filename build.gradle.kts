import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.expediagroup.graphql") version "6.2.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup:graphql-kotlin-spring-client:6.2.2")

    testImplementation(kotlin("test"))
}

graphql {
    client {
        schemaFile = file("$projectDir/src/main/resources/schema/schema.graphqls")
        packageName = "org.example.generated"
        queryFiles = listOf(
            file("$projectDir/src/main/resources/queries/withoutFragments.graphql"),
            file("$projectDir/src/main/resources/queries/withFragments.graphql"),
        )
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
