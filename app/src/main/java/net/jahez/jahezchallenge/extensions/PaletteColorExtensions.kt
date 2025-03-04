/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.extensions

import androidx.palette.graphics.Palette

fun Palette?.paletteColorList(): List<Int> {
  return listOf(
    this?.lightVibrantSwatch?.rgb,
    this?.lightMutedSwatch?.rgb,
    this?.vibrantSwatch?.rgb,
    this?.mutedSwatch?.rgb,
    this?.darkVibrantSwatch?.rgb,
    this?.darkMutedSwatch?.rgb,
    this?.dominantSwatch?.rgb
  ).map { it ?: 0 }
}
