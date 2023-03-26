package com.example.geolearnapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.geolearnapp.ui.learn.LearnScreen
import com.example.geolearnapp.ui.quiz.*
//import com.example.geolearnapp.ui.quiz.quizGraph
import com.example.geolearnapp.ui.theme.GeoLearnAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoLearnAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Learn",
                                    route = "learn",
                                    icon = Icons.Default.Book
                                ),
                                BottomNavItem(
                                    name = "Quiz",
                                    route = "quiz",
                                    icon = Icons.Default.Gamepad
                                )
                            ),
                            navController = navController,
                            onItemClick = { navController.navigate(it.route) }
                        )
                    }
                ) {
                    Column(modifier = Modifier.padding(paddingValues = it)) {
                        HomeNavGraph(navController = navController)
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "learn") {
        composable("learn") {
            BackHandler(true) {
                //nothing
            }
            LearnScreen()
        }
        composable("quiz") {
            BackHandler(true) {
                //nothing
            }
            QuizScreen(navController)
        }
        composable("multipleChoices")
        {
            MultipleChoicesScreen(navController)
        }
        composable("trueFalse") {
            TrueFalseScreen(navController)
        }
        composable("written") {
            WrittenScreen()
        }
        composable("matching") {
            MatchingScreen()
        }

    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val screens = listOf("learn", "quiz")

    val currentDestination = backStackEntry.value?.destination

    val bottomBarDestinations = screens.any { it == currentDestination?.route }

    if (!bottomBarDestinations) {
        return
    }

    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.White,
        elevation = 20.dp,
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = MaterialTheme.colors.primary,
                //0xff00ced1
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                        )
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}


