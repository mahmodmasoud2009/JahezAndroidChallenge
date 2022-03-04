/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.details

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.jahez.jahezchallenge.persistence.PosterDao
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val posterDao: PosterDao
) {

  @WorkerThread
  fun getPosterById(id: Long) = flow {
    val poster = posterDao.getPoster(id)
    emit(poster)
  }.flowOn(Dispatchers.IO)
}
