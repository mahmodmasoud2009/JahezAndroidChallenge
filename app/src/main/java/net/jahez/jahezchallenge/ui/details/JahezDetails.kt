/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.details
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.palette.BitmapPalette
import net.jahez.jahezchallenge.ui.custom.ImageBalloonAnchor
import net.jahez.jahezchallenge.utils.NetworkImage
import net.jahez.jahezchallenge.model.Entity

@Composable
fun PosterDetails(
    posterId: Long,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {}
) {
  LaunchedEffect(key1 = posterId) {
    viewModel.loadPosterById(posterId)
  }

  val details: Entity? by viewModel.posterDetailsFlow.collectAsState(initial = null)
  details?.let { poster ->
    PosterDetailsBody(poster, pressOnBack)
  }
}

@Composable
private fun PosterDetailsBody(
  poster: Entity,
  pressOnBack: () -> Unit
) {
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .background(MaterialTheme.colors.background)
      .fillMaxHeight()
  ) {
    var palette by remember { mutableStateOf<Palette?>(null) }

    ConstraintLayout {
      val (arrow, image, title, content) = createRefs()

      NetworkImage(
        url = poster.image,
        modifier = Modifier
          .constrainAs(image) {
            top.linkTo(parent.top)
          }
          .fillMaxWidth()
          .aspectRatio(0.85f),
        circularRevealEnabled = true,
        bitmapPalette = BitmapPalette {
          palette = it
        }
      )



      Text(
        text = poster.name,
        style = MaterialTheme.typography.h1,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier
          .constrainAs(title) {
            top.linkTo(image.bottom)
          }
          .padding(start = 16.dp, top = 12.dp)
      )

      Text(
        text = poster.description,
        style = MaterialTheme.typography.body2,
        modifier = Modifier
          .constrainAs(content) {
            top.linkTo(title.bottom)
          }
          .padding(16.dp)
      )
      ImageBalloonAnchor(
        reference = image,
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(0.85f),
        content = poster.name,
        onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
      )

      Icon(
        imageVector = Icons.Filled.ArrowBack,
        tint = Color.White,
        contentDescription = null,
        modifier = Modifier
          .constrainAs(arrow) {
            top.linkTo(parent.top)
          }
          .padding(12.dp)
          .clickable(onClick = { pressOnBack() })
      )
    }
  }
}
