import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

//kotlin {
//    androidTarget {
//        compilations.all {
//            compileTaskProvider.configure {
//                compilerOptions {
//                    jvmTarget.set(JvmTarget.JVM_1_8)
//                }
//            }
//        }
//    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }
//
//    sourceSets {
//        commonMain.dependencies {
//            //put your multiplatform dependencies here
//        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
//    }
//}
//
//android {
//    namespace = "com.zoho.vtouch.layoutruleevaluator"
//    compileSdk = 34
//    defaultConfig {
//        minSdk = 24
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//}

tasks.register<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        publishLibraryVariants("release", "debug")
    }


    iosX64()
    iosArm64()
    iosSimulatorArm64()

//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "13.0"
//        pod("SQLCipher", "~> 4.0") //Add SQLCipher pod
//        framework {
//            baseName = "ZPCore"
////            freeCompilerArgs += listOf("-Xbinary=bundleId=com.zoho.zprojectsclientcore")
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


android {
    namespace = "com.zoho.vtouch.layoutruleevaluator"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
        targetSdk = 34
    }
}


