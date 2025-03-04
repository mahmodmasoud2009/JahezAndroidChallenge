/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDatabase {
  lateinit var db: AppDatabase

  @Before
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
  }

  @After
  fun closeDB() {
    db.close()
  }
}
