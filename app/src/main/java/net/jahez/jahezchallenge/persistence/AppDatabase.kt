/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import net.jahez.jahezchallenge.model.Entity

@Database(entities = [Entity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

  abstract fun posterDao(): JahezDao
}
