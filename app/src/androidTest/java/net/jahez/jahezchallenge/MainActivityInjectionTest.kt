/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import net.jahez.jahezchallenge.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityInjectionTest {

  @get:Rule
  val hiltRule = HiltAndroidRule(this)

  @Test
  fun verifyInjection() {
    ActivityScenario.launch(MainActivity::class.java).use {
      it.moveToState(Lifecycle.State.CREATED)
      it.onActivity { activity ->
        assertThat(activity.viewModel).isNotNull()
        assertThat(activity.viewModel.imageLoader).isNotNull()
      }
    }
  }
}
