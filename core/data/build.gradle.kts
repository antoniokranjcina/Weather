plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.androidKotlin)
}

android {
    namespace = ProjectConfig.nameSpaceCoreData
    compileSdk = 33

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
}

dependencies {
    implementation(project(Module.coreDataSourceRemote))
    implementation(project(Module.coreDataSourceLocal))
    implementation(project(Module.coreModel))

    implementation(AndroidX.coreKtx)
    implementation(Koin.koinAndroid)

    testImplementation(UnitTesting.junit4)
    testImplementation(UnitTesting.truth)
    testImplementation(UnitTesting.coroutinesTest)
    testImplementation(UnitTesting.mociktoInline)
    testImplementation(UnitTesting.mockitoCore)
}