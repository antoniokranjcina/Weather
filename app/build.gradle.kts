plugins {
    id(GradlePlugins.androidApplication)
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

    implementation(project(Module.coreDataSourceRemote))
    implementation(project(Module.coreDataSourceLocal))
    implementation(project(Module.coreModel))
    implementation(project(Module.coreData))
    implementation(project(Module.coreUi))

    implementation(project(Module.featureHome))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleRuntimeCompose)

    implementation(WorkManager.workManagerRuntime)

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

}