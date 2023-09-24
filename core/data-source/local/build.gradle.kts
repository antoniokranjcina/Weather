plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.androidKotlin)
    id(GradlePlugins.kotlinKapt)
}

android {
    namespace = ProjectConfig.nameSpaceCoreDataSourceLocal
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
}

dependencies {
    implementation(Koin.koinAndroid)

    implementation(Room.room)
    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)

    testImplementation(UnitTesting.junit4)
    testImplementation(UnitTesting.truth)
    testImplementation(UnitTesting.coroutinesTest)
    testImplementation(UnitTesting.mociktoInline)
    testImplementation(UnitTesting.mockitoCore)
    testImplementation(Room.roomTesting)
}
