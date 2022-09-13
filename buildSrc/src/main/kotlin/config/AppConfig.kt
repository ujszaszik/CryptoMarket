package config

import org.gradle.api.JavaVersion
import signing.SigningConfig

object AppConfig {

    val VERSION_JAVA = JavaVersion.VERSION_11
    const val JVM_TARGET = "11"

    private const val COMPILE_SDK = 32
    private const val MIN_SDK = 24
    private const val TARGET_SDK = 32

    private const val JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    private const val HILT_RUNNER = "com.swissborg.cryptomarket.hilt.HiltTestRunner"

    val defaultConfig = ApplicationConfig(
        applicationId = "com.swissborg.cryptomarket",
        compileSdk = COMPILE_SDK,
        minSdk = MIN_SDK,
        targetSdk = TARGET_SDK,
        versionCode = 1,
        versionName = "1.0.0",
        buildToolsVersion = "31.0.0",
        testInstrumentationRunner = JUNIT_RUNNER
    )

    val signingConfig = SigningConfig(
        keyAlias = "KEY_ALIAS",
        keyPassword = "KEY_PASSWORD",
        storeFilePath = "store/file/path.jks",
        storePassword = "STORE_PASSWORD"
    )
}