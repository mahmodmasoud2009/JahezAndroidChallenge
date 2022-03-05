/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.jahez.jahezchallenge.model.Entity

@Dao
interface JahezDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPosterList(posters: List<Entity>)

  @Query("SELECT * FROM Entity WHERE id = :id_")
  suspend fun getPoster(id_: Long): Entity?

  @Query("SELECT * FROM Entity")
  suspend fun getPosterList(): List<Entity>
}
