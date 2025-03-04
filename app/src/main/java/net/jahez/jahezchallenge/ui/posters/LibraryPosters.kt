/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import net.jahez.jahezchallenge.ui.custom.StaggeredVerticalGrid
import net.jahez.jahezchallenge.ui.theme.DisneyComposeTheme
import net.jahez.jahezchallenge.utils.NetworkImage
import net.jahez.jahezchallenge.model.Entity

@Composable
fun LibraryPosters(
    modifier: Modifier = Modifier,
    posters: List<Entity>,
    selectPoster: (Long) -> Unit = {},
) {
  Column(
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .statusBarsPadding()
      .background(MaterialTheme.colors.background)
  ) {
    StaggeredVerticalGrid(
      maxColumnWidth = 330.dp,
      modifier = Modifier.padding(4.dp)
    ) {
      posters.forEach { poster ->
        LibraryPoster(poster = poster, selectPoster = selectPoster)
      }
    }
  }
}

@Composable
private fun LibraryPoster(
    modifier: Modifier = Modifier,
    poster: Entity,
    selectPoster: (Long) -> Unit = {},
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(4.dp)
      .clickable(
        onClick = { selectPoster(poster.id) }
      ),
    color = MaterialTheme.colors.onBackground,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
      val (image, title, content) = createRefs()
      NetworkImage(
        url = poster.image,
        modifier = Modifier
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
            bottom.linkTo(title.top)
          }
          .height(112.dp)
          .aspectRatio(1.0f)
          .clip(CircleShape)
      )

      Text(
        text = poster.name,
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .constrainAs(title) {
            centerHorizontallyTo(parent)
            top.linkTo(image.bottom)
          }
          .padding(8.dp)
      )

      Text(
        text = poster.hours,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .constrainAs(content) {
            centerHorizontallyTo(parent)
            top.linkTo(title.bottom)
          }
          .padding(horizontal = 8.dp)
      )
    }
  }
}

@Composable
@Preview(name = "LibraryPoster Light")
private fun LibraryPosterPreviewLight() {
  DisneyComposeTheme(darkTheme = false) {
    LibraryPoster(
      poster = Entity.mock()
    )
  }
}

@Composable
@Preview(name = "LibraryPoster Dark")
private fun LibraryPosterPreviewDark() {
  DisneyComposeTheme(darkTheme = true) {
    LibraryPoster(
      poster = Entity.mock()
    )
  }
}
