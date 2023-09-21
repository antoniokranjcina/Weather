plugins {
    id(GradlePlugins.android)
    id(GradlePlugins.androidKotlin)
}

android {
    namespace = ProjectConfig.nameSpaceApp
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.nameSpaceApp
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)

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
    implementation(Compose.navigationCompose)
}