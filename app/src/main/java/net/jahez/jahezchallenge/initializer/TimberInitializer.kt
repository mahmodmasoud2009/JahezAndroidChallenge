/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

@file:Suppress("unused")

package net.jahez.jahezchallenge.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      Timber.d("TimberInitializer is initialized.")
    }
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
