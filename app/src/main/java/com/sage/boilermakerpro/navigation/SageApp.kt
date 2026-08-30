package com.sage.boilermakerpro.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sage.boilermakerpro.screens.AcademyScreen
import com.sage.boilermakerpro.screens.CalculatorsScreen
import com.sage.boilermakerpro.screens.DashboardScreen
import com.sage.boilermakerpro.screens.JobEstimatorScreen
import com.sage.boilermakerpro.screens.LibraryScreen
import com.sage.boilermakerpro.screens.ProfileScreen
import com.sage.boilermakerpro.screens.ProjectPlannerScreen
import com.sage.boilermakerpro.screens.SafetyScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Filled.Home)
    object Calculators : Screen("calculators", "Calculators", Icons.Filled.Calculate)
    object Library : Screen("library", "Library", Icons.Filled.MenuBook)
    object Safety : Screen("safety", "Safety", Icons.Filled.HealthAndSafety)
    object Projects : Screen("projects", "Projects", Icons.Filled.Assignment)
    object Estimator : Screen("estimator", "Estimator", Icons.Filled.RequestQuote)
    object Academy : Screen("academy", "Academy", Icons.Filled.School)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}

val bottomNavItems = listOf(
    Screen.Dashboard,
    Screen.Calculators,
    Screen.Library,
    Screen.Safety,
    Screen.Projects,
    Screen.Estimator,
    Screen.Academy,
    Screen.Profile
)

@Composable
fun SageApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = Screen.Dashboard.route, modifier = Modifier.padding(padding)) {
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Calculators.route) { CalculatorsScreen() }
            composable(Screen.Library.route) { LibraryScreen() }
            composable(Screen.Safety.route) { SafetyScreen() }
            composable(Screen.Projects.route) { ProjectPlannerScreen() }
            composable(Screen.Estimator.route) { JobEstimatorScreen() }
            composable(Screen.Academy.route) { AcademyScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
