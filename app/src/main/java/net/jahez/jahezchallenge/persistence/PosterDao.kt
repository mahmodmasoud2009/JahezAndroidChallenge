/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.jahez.jahezchallenge.model.Poster

@Dao
interface PosterDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPosterList(posters: List<Poster>)

  @Query("SELECT * FROM Poster WHERE id = :id_")
  suspend fun getPoster(id_: Long): Poster?

  @Query("SELECT * FROM Poster")
  suspend fun getPosterList(): List<Poster>
}
