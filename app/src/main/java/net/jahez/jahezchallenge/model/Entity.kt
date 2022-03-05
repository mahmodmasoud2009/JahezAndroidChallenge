/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Entity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val hours: String,
    val image: String,
    val rating: Int,
    val distance: Double,
    val hasOffer: Boolean
) {

    companion object {

        fun mock() = Entity(
            id = 0,
            name = "Kudu",
            description = "Enjoy fast delivery from Jahez. Order now, or schedule your order any time you want",
            hours = "05:30 AM - 04:30 AM",
            image = "https://jahez-other-oniiphi8.s3.eu-central-1.amazonaws.com/1.jpg",
            rating = 8,
            distance = 0.8866873944226332,
            hasOffer = false
        )
    }
}
