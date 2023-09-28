plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.androidKotlin)
}

android {
    namespace = ProjectConfig.nameSpaceCoreUi
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

    implementation(platform(Compose.BOM))
    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.material)

}