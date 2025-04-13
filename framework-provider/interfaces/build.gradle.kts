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
}

dependencies {
    implementation(libs.dagger)
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":domain:model"))

    testImplementation(libs.dagger)
    testFixturesImplementation(libs.dagger)
    testFixturesImplementation(libs.kotlinx.coroutines.core)
    testFixturesImplementation(project(":domain:model"))
    ksp(libs.dagger.compiler)
}
