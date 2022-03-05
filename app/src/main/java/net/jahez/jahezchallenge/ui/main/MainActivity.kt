/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.ui.root.RootViewModel
import net.jahez.jahezchallenge.ui.theme.DisneyComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @VisibleForTesting
  internal val viewModel: RootViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
        DisneyComposeTheme {
          DisneyMainScreen()
        }
      }
    }
  }
}
