/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.posters

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.ui.theme.purple200
import net.jahez.jahezchallenge.extensions.visible
import net.jahez.jahezchallenge.model.Entity
import net.jahez.jahezchallenge.ui.settings.SettingsLanguage
import net.jahez.jahezchallenge.ui.main.MainViewModel

@Composable
fun Posters(
  viewModel: MainViewModel,
  selectPoster: (Long) -> Unit
) {
  val posters: List<Entity> by viewModel.posterList.collectAsState(initial = listOf())
  val isLoading: Boolean by viewModel.isLoading
  val selectedTab = HomeTab.getTabFromResource(viewModel.selectedTab.value)
  val tabs = HomeTab.values()

  ConstraintLayout {
    val (body, progress) = createRefs()
    Scaffold(
      backgroundColor = MaterialTheme.colors.primarySurface,
      topBar = { PosterAppBar() },
      modifier = Modifier.constrainAs(body) {
        top.linkTo(parent.top)
      },
      bottomBar = {
        BottomNavigation(
          backgroundColor = purple200,
          modifier = Modifier
            .navigationBarsHeight(56.dp)
        ) {
          tabs.forEach { tab ->
            BottomNavigationItem(
              icon = { Icon(imageVector = tab.icon, contentDescription = null) },
              label = { Text(text = stringResource(tab.title), color = Color.White) },
              selected = tab == selectedTab,
              onClick = { viewModel.selectTab(tab.title) },
              selectedContentColor = LocalContentColor.current,
              unselectedContentColor = LocalContentColor.current,
              modifier = Modifier.navigationBarsPadding()
            )
          }
        }
      }
    ) { innerPadding ->
      val modifier = Modifier.padding(innerPadding)
      Crossfade(selectedTab) { destination ->
        when (destination) {
          HomeTab.HOME -> RadioPosters(modifier, posters, selectPoster)
          HomeTab.Settings -> SettingsLanguage()
        }
      }
    }
    CircularProgressIndicator(
      modifier = Modifier
        .constrainAs(progress) {
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .visible(isLoading)
    )
  }
}

@Preview
@Composable
private fun PosterAppBar() {
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier.height(58.dp),

  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterVertically),
      text = stringResource(R.string.app_name),
      color = Color.White,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold
    )
  }
}

enum class HomeTab(
  @StringRes val title: Int,
  val icon: ImageVector
) {
  HOME(R.string.menu_home, Icons.Filled.Home),
  Settings(R.string.menu_settings, Icons.Filled.Settings);

  companion object {
    fun getTabFromResource(@StringRes resource: Int): HomeTab {
      return when (resource) {
        R.string.menu_settings -> Settings
        else -> HOME
      }
    }
  }
}
