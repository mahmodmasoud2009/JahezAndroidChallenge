/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val detailRepository: DetailRepository
) : ViewModel() {

  private val posterIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

  val posterDetailsFlow = posterIdSharedFlow.flatMapLatest {
    detailRepository.getPosterById(it)
  }

  init {
    Timber.d("init DetailViewModel")
  }

  fun loadPosterById(id: Long) = posterIdSharedFlow.tryEmit(id)
}
