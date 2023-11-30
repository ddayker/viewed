plugins {
    id("com.dayker.android-library")
}

android {
    namespace = "com.dayker.viewed.network"
    buildFeatures {
        buildConfig = true
        compose = true
        androidResources = true
    }
}

dependencies {
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.accompanist.adaptive)
    implementation(libs.accompanist.testharness)
    implementation(libs.work.runtime.ktx)
    implementation(libs.collection.ktx)
    implementation(libs.foundation)
    implementation(libs.animation)
    implementation(libs.ui.util)
    implementation(libs.runtime)
    implementation(libs.lifecycle.service)
    implementation(libs.lifecycle.process)
    implementation(libs.symbol.processing.api)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.core)
    implementation(libs.material3.window.size)

    implementation(projects.core)
}