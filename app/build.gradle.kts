import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import config.AppConfig
import config.appConfig
import dependencies.Plugins
import dependencies.libs.*
import flavor.Flavor
import flavor.createFlavors
import org.bouncycastle.util.Properties
import org.gradle.internal.impldep.com.thoughtworks.qdox.JavaDocBuilder.load
import signing.release

plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("io.gitlab.arturbosch.detekt").version("1.21.0")
}

android {

    appConfig(AppConfig.defaultConfig)

    signingConfigs { release(AppConfig.signingConfig) }

    createFlavors(dimensionName = "env", flavors = Flavor.values().asList())

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            versionNameSuffix = gradleLocalProperties(rootDir).getProperty("versionNameSuffix")
        }
    }

    configureDetekt()

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibs.VERSION_COMPOSE
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
        animationsDisabled = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.excludes.add("**/attach_hotspot_windows.dll")
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementations(KotlinLibs.dependencies())
    implementations(AndroidLibs.dependencies())

    implementations(ComposeLibs.dependencies())
    implementations(CustomLibs.dependencies())
    implementations(NetworkLibs.dependencies())

    implementations(HiltLibs.dependencies())
    kapts(HiltLibs.kaptDependencies())

    testImplementations(TestLibs.testDependencies())
    kaptTests(TestLibs.kaptTestDependencies())

    implementation(FIREBASE_BOM)
    detektPlugins(Plugins.DETEKT_FORMATTER)
}

fun buildProperties(): Properties {
    val props = Properties().apply {
        val localProperties = File(rootProject.rootDir, "local.properties")
        load(localProperties)
    }
    return props
}

fun configureDetekt() {
    detekt {
        config = files("detekt_config.yml")
        parallel = true
        buildUponDefaultConfig = false
        allRules = true
        debug = true
        basePath = projectDir.absolutePath
        reports {
            xml { enabled = false }
            html { enabled = false }
            txt {
                enabled = true
                destination = file("build/reports/detekt.txt")
            }
        }
    }
}