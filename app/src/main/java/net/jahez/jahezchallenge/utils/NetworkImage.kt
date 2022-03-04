/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.ui.theme.shimmerHighLight

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading disney poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */
@Preview
@Composable
fun NetworkImage(
  @PreviewParameter(NetworkUrlPreviewProvider::class) url: String,
  modifier: Modifier = Modifier,
  circularRevealEnabled: Boolean = false,
  contentScale: ContentScale = ContentScale.Crop,
  bitmapPalette: BitmapPalette? = null
) {
  CoilImage(
    imageModel = url,
    modifier = modifier,
    contentScale = contentScale,
    circularReveal = CircularReveal(duration = 300).takeIf { circularRevealEnabled },
    bitmapPalette = bitmapPalette,
    previewPlaceholder = R.drawable.poster,
    shimmerParams = ShimmerParams(
      baseColor = MaterialTheme.colors.background,
      highlightColor = shimmerHighLight,
      dropOff = 0.65f
    ),
    failure = {
      Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "image request failed.",
          style = MaterialTheme.typography.body2
        )
      }
    },
  )
}
