import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) = commonExtension.apply {

    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        testInstrumentationRunner = Config.testRunner
        proguardFiles(Config.proguardFile)
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
        useLiveLiterals = true
    }
}

fun Project.configureAndroidLibrary(variant: LibraryExtension) = variant.apply {
    configureAndroid(this)

    defaultConfig {
        consumerProguardFiles(file(Config.consumerProguardFile))
    }

    libraryVariants.all {
        sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

fun Project.configureAndroidApplication(variant: BaseAppModuleExtension) = variant.apply {
    configureAndroid(this)
    applicationVariants.all {
        sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile(Config.defaultProguardFile),
                    Config.proguardFile
                )
            }
        }
    }
}