import org.gradle.api.JavaVersion

object Config {

    const val applicationId = "com.dayker.viewed"
    const val versionCode = 1
    const val majorRelease = 1
    const val minorRelease = 0
    const val patch = 0
    const val kotlinCompilerExtensionVersion = "1.4.3"
    const val versionName = "$majorRelease.$minorRelease.$patch ($versionCode)"
    val javaVersion = JavaVersion.VERSION_17
    val jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    const val compileSdk = 34
    const val targetSdk = compileSdk
    const val minSdk = 26
    val kotlinLanguageVersion = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_8
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val defaultProguardFile = "proguard-android-optimize.txt"
    const val proguardFile = "proguard-rules.pro"
    const val consumerProguardFile = "consumer-rules.pro"
}