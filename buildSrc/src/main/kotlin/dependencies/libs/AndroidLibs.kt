package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object AndroidLibs : DependencyProvider {

    private const val VERSION_ACTIVITY_KTX = "1.5.1"
    private const val VERSION_APP_COMPAT = "1.5.0"
    private const val VERSION_MATERIAL_DESIGN = "1.6.1"

    override fun dependencies() = listOf(
        Dependency("androidx.activity", "activity-ktx", VERSION_ACTIVITY_KTX),
        Dependency("androidx.appcompat", "appcompat", VERSION_APP_COMPAT),
        Dependency("com.google.android.material", "material", VERSION_MATERIAL_DESIGN),
    ).values()

}

