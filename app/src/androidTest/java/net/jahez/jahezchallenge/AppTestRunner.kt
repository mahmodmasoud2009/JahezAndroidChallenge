/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

@Suppress("unused")
class AppTestRunner : AndroidJUnitRunner() {
  override fun newApplication(
    cl: ClassLoader?,
    className: String?,
    context: Context?
  ): Application = super.newApplication(cl, HiltTestApplication::class.java.name, context)
}
