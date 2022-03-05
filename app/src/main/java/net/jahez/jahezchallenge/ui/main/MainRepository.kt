/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import net.jahez.jahezchallenge.model.Entity
import net.jahez.jahezchallenge.network.JahezService
import net.jahez.jahezchallenge.persistence.JahezDao
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val disneyService: JahezService,
  private val posterDao: JahezDao
) {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadDisneyPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Entity> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      disneyService.fetchDisneyPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request gets an error response.
        // e.g. internal server error.
        .onError {
          onError(message())
        }
        // handle the case when the API request gets an exception response.
        // e.g. network connection error.
        .onException {
          onError(message())
        }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}
