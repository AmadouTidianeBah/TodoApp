package com.atb.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atb.todo.ui.detail.TodoDetail
import com.atb.todo.ui.home.TodoHome

@Composable
fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
        modifier = modifier
    ) {
        composable(route = Route.Home.route) {
            TodoHome(onNavigate = {
                navController.navigate(Route.Detail.route + "/${it?.id ?: -1}")
            })
        }
        
        composable(
            route = Route.Detail.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            TodoDetail(
                selectedId = it.arguments?.getInt("id") ?: -1,
                onNavigate = { navController.navigateUp() }
            )
        }
    }
}

sealed class Route(val route: String) {
    object Home: Route("home_route")
    object Detail: Route("detail_route")
}