/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SettingsLanguage(
) {
    PosterDetailsBody()
}

@Composable
private fun PosterDetailsBody(
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        ConstraintLayout {
            val ( title, content) = createRefs()
            Text(
                text = "change language",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
                    .padding(16.dp)
            )

        }
    }
}
