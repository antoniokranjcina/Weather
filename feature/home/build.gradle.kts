plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.androidKotlin)
}

android {
    namespace = ProjectConfig.nameSpaceFeatureHome
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
    }
    compileOptions {
        sourceCompatibility = ProjectConfig.java
        targetCompatibility = ProjectConfig.java
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.targetJDK
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
}

dependencies {

    implementation(project(Module.coreUi))
    implementation(project(Module.coreModel))
    implementation(project(Module.coreData))
    implementation(project(Module.coreDataSourceLocal)) // TODO delete later

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleRuntimeCompose)

    implementation(platform(Compose.BOM))
    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material)
    implementation(Compose.activity)
    implementation(Compose.runtime)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.uiTestManif)

    implementation(Koin.koinAndroid)
    implementation(Compose.koinAndroidCompose)

    implementation(Compose.navigationCompose)

    implementation(Coil.coilCompose)

}