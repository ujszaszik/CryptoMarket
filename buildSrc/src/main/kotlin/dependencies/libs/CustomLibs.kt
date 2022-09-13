package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

object CustomLibs : DependencyProvider {

    private const val VERSION_ABP = "1.4.0"
    private const val VERSION_TIMBER = "5.0.1"
    internal const val VERSION_FIREBASE = "30.2.0"

    override fun dependencies() = listOf(
        Dependency("com.jakewharton.threetenabp", "threetenabp", VERSION_ABP),
        Dependency("com.jakewharton.timber", "timber", VERSION_TIMBER)
    ).values()
}

val DependencyHandlerDelegate.FIREBASE_BOM: org.gradle.api.artifacts.Dependency
    get() = platform("com.google.firebase:firebase-bom:${CustomLibs.VERSION_FIREBASE}")