import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.dayker.android-library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}


val appProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.dayker.viewed.watched"
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${appProperties.getProperty("API_KEY")}\"")
    }
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
    implementation(libs.core)
    implementation(libs.calendar)
    implementation(libs.duration)
    implementation(libs.input)
    implementation(libs.list)
    implementation(libs.state)
    implementation(libs.androidx.room.ktx)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.cio)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.androidx.palette)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
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
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.common.ktx)
    implementation(libs.constraintlayout.compose)
    implementation(libs.runtime)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.datastore.preferences)
    implementation(libs.lifecycle.service)
    implementation(libs.lifecycle.process)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.symbol.processing.api)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.core)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.material3.window.size)
    ksp(libs.room.compiler)

    implementation(projects.authentication)
    implementation(projects.core)
}