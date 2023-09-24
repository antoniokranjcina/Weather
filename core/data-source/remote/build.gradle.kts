plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.androidKotlin)
}

android {
    namespace = ProjectConfig.nameSpaceCoreDataSourceRemote
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", ApiData.WeatherUrlName, ApiData.WeatherUrl)
            buildConfigField("String", ApiData.WeatherApiKeyName, ApiData.WeatherApiKey)
        }
        release {
            buildConfigField("String", ApiData.WeatherUrlName, ApiData.WeatherUrl)
            buildConfigField("String", ApiData.WeatherApiKeyName, ApiData.WeatherApiKey)
        }
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
    implementation(project(Module.coreDataSourceLocal))

    implementation(Koin.koinAndroid)

    implementation(Retrofit.retrofit2)
    implementation(Retrofit.retrofit2ConverterGson)
    implementation(Retrofit.retrofitLoggingInterceptor)

    testImplementation(UnitTesting.junit4)
    testImplementation(UnitTesting.truth)
    testImplementation(UnitTesting.coroutinesTest)
    testImplementation(UnitTesting.mociktoInline)
    testImplementation(UnitTesting.mockitoCore)
}
