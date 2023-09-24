import org.gradle.api.JavaVersion

object ProjectConfig {

    const val nameSpaceApp = "com.antoniok.weather"
    const val nameSpaceCoreDataSourceRemote = "$nameSpaceApp.core.data_source.remote"
    const val nameSpaceCoreDataSourceLocal = "$nameSpaceApp.core.data_source.local"
    const val nameSpaceCoreUi = "$nameSpaceApp.core.ui"
    const val nameSpaceCoreData = "$nameSpaceApp.core.data"

    const val nameSpaceFeatureHome = "$nameSpaceApp.feature.home"

    const val compileSdk = 33
    const val minSdk = 29
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    val java = JavaVersion.VERSION_17
    const val targetJDK = "17"

}