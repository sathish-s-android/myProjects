

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1")
        }
        google()
        mavenCentral()
        maven {
            url  = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.javassist:javassist:3.27.0-GA")
//        classpath(files("app/MAMSDK/com.microsoft.intune.mam.build.jar"))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.24")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        jcenter()
        google()
        mavenCentral()
    }
}

rootProject.name = "intune play app"
include(":app")
