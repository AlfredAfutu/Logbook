plugins {
    id("java-library")
    id("java-test-fixtures")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }

    tasks.test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(libs.dagger)
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":domain:model"))
    implementation(project(":framework-provider:interfaces"))

    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)
    testImplementation(libs.junit5.platform)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.dagger)
    testImplementation(testFixtures(project(":framework-provider:interfaces")))

    ksp(libs.dagger.compiler)
}
