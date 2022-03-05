/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NetworkUrlPreviewProvider : PreviewParameterProvider<String> {
  override val count: Int
    get() = super.count
  override val values: Sequence<String>
    get() = sequenceOf("https://jahez-other-oniiphi8.s3.eu-central-1.amazonaws.com/1.jpg")
}
