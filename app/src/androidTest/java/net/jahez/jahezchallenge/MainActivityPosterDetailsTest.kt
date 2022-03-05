/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import net.jahez.jahezchallenge.ui.details.DetailViewModel
import net.jahez.jahezchallenge.ui.details.PosterDetails
import net.jahez.jahezchallenge.ui.main.MainActivity
import net.jahez.jahezchallenge.ui.theme.DisneyComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityPosterDetailsTest {

  @get:Rule
  val hiltRule = HiltAndroidRule(this)

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  private lateinit var activity: MainActivity

  @Before
  fun init() {
    composeTestRule.activityRule.scenario.onActivity {
      activity = it
    }
  }

  @Test
  fun posterDetailsFrozenIILoadingTest() {
    composeTestRule.setContent {
      DisneyComposeTheme {

        val viewModel = hiltViewModel<DetailViewModel>()
        viewModel.loadPosterById(0)

        PosterDetails(
          posterId = 0,
          viewModel = viewModel
        )
      }
    }

    composeTestRule
      .onNodeWithText("Frozen II", ignoreCase = true)
      .assertIsDisplayed()
  }

  @Test
  fun posterDetailsZootopiaLoadingTest() {
    composeTestRule.setContent {
      DisneyComposeTheme {
        val viewModel = hiltViewModel<DetailViewModel>()
        PosterDetails(
          posterId = 0,
          viewModel = viewModel
        )
      }
    }

    composeTestRule
      .onNodeWithText("Zootopia", ignoreCase = true)
      .assertIsDisplayed()
  }
}
