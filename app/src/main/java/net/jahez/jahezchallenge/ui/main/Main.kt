/*
 * Designed and developed by 2022 Mahmoud Masoud
 */
package net.jahez.jahezchallenge.ui.main
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import net.jahez.jahezchallenge.ui.details.PosterDetails
import net.jahez.jahezchallenge.ui.posters.Posters

@Preview
@Composable
fun DisneyMainScreen() {
  val navController = rememberNavController()

  ProvideWindowInsets {
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
      composable(NavScreen.Home.route) {
        Posters(
          viewModel = hiltViewModel(),
          selectPoster = {
            navController.navigate("${NavScreen.PosterDetails.route}/$it")
          }
        )
      }
      composable(
        route = NavScreen.PosterDetails.routeWithArgument,
        arguments = listOf(
          navArgument(NavScreen.PosterDetails.argument0) { type = NavType.LongType }
        )
      ) { backStackEntry ->
        val posterId =
          backStackEntry.arguments?.getLong(NavScreen.PosterDetails.argument0) ?: return@composable

        PosterDetails(posterId = posterId, viewModel = hiltViewModel()) {
          navController.navigateUp()
        }
      }
    }
  }
}

sealed class NavScreen(val route: String) {

  object Home : NavScreen("Home")

  object PosterDetails : NavScreen("PosterDetails") {

    const val routeWithArgument: String = "PosterDetails/{posterId}"

    const val argument0: String = "posterId"
  }
}
