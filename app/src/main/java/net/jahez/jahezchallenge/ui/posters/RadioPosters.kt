/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import net.jahez.jahezchallenge.ui.theme.DisneyComposeTheme
import net.jahez.jahezchallenge.utils.NetworkImage
import net.jahez.jahezchallenge.model.Entity

@Composable
fun RadioPosters(
    modifier: Modifier = Modifier,
    posters: List<Entity>,
    selectPoster: (Long) -> Unit = {},
) {
  val listState = rememberLazyListState()
  Column(
    modifier = modifier
      .statusBarsPadding()
      .background(MaterialTheme.colors.background)
  ) {
    LazyColumn(
      state = listState,
      contentPadding = PaddingValues(4.dp)
    ) {
      items(
        items = posters,
        itemContent = { poster ->
          RadioPoster(
            poster = poster,
            selectPoster = selectPoster
          )
        }
      )
    }
  }
}

@Composable
private fun RadioPoster(
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
    ConstraintLayout(
      modifier = Modifier.padding(8.dp)
    ) {
      val (image, title, content) = createRefs()

      NetworkImage(
        modifier = Modifier
          .constrainAs(image) {
            centerVerticallyTo(parent)
            end.linkTo(title.start)
          }
          .height(64.dp)
          .aspectRatio(1f)
          .clip(RoundedCornerShape(4.dp)),
        url = poster.image
      )

      Text(
        modifier = Modifier
          .constrainAs(title) {
            start.linkTo(image.end)
          }
          .padding(horizontal = 12.dp),
        text = poster.name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2
      )

      Text(
        modifier = Modifier
          .constrainAs(content) {
            start.linkTo(image.end)
            top.linkTo(title.bottom)
          }
          .padding(start = 12.dp, top = 4.dp),
        text = poster.hours,
        style = MaterialTheme.typography.body2,
      )
    }
  }
}

@Composable
@Preview(name = "RadioPoster Light")
private fun RadioPosterPreviewLight() {
  DisneyComposeTheme(darkTheme = false) {
    RadioPoster(
      poster = Entity.mock(),
      selectPoster = { }
    )
  }
}

@Composable
@Preview(name = "RadioPoster Dark")
private fun RadioPosterPreviewDark() {
  DisneyComposeTheme(darkTheme = true) {
    RadioPoster(
      poster = Entity.mock(),
      selectPoster = { }
    )
  }
}
