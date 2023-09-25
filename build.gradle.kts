// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(GradlePlugins.androidApplication) version GradlePlugins.androidVersion apply false
    id(GradlePlugins.androidLibrary) version GradlePlugins.androidVersion apply false
    id(GradlePlugins.androidKotlin) version Kotlin.version apply false
}