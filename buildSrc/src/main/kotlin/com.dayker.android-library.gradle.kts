plugins {
    id("com.android.library")
    kotlin("android")
}
val libs by versionCatalog

android {
    configureAndroidLibrary(this)
}

dependencies {
    implementation(libs.requireLib("core-ktx"))
}