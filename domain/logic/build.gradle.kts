plugins {
    id("java-library")
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
    implementation(project(":data:repository"))
    implementation(project(":domain:model"))
    implementation(libs.dagger)

    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)

    ksp(libs.dagger.compiler)
}

