/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.persistence
import kotlinx.coroutines.runBlocking
import net.jahez.jahezchallenge.model.Entity
import net.jahez.jahezchallenge.utils.MockTestUtil.mockPosterList
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PosterDaoTest : LocalDatabase() {

  private lateinit var posterDao: JahezDao

  @Before
  fun init() {
    posterDao = db.posterDao()
  }

  @Test
  fun insertAndLoadPosterListTest() = runBlocking {
    val mockDataList = mockPosterList()
    posterDao.insertPosterList(mockDataList)

    val loadFromDB = posterDao.getPosterList()
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = Entity.mock()
    assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
  }
}
