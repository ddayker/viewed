import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.services)
    }
}

allprojects {
    tasks {
        withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget = Config.jvmTarget
                languageVersion = Config.kotlinLanguageVersion
            }
        }
    }
}