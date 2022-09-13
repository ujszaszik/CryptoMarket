package com.swissborg.cryptomarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost

internal fun NavHostController.navigate(host: Host) = navigate(host.route)

internal fun NavGraphBuilder.composable(
    host: Host,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.route = host.route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

@Composable
internal fun NavGraph(
    router: Router,
    rootHost: Host,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        router.controller(),
        remember(null, rootHost.route, builder) {
            router.controller().createGraph(rootHost.route, null, builder)
        },
        Modifier
    )
}