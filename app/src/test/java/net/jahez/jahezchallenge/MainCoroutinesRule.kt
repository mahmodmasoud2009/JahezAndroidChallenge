/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

@file:Suppress("SpellCheckingInspection")

package net.jahez.jahezchallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope() {

  private val testCoroutinesDispatcher = TestCoroutineDispatcher()

  override fun apply(base: Statement?, description: Description?) = object : Statement() {
    override fun evaluate() {
      Dispatchers.setMain(testCoroutinesDispatcher)
      base?.evaluate()
      Dispatchers.resetMain()
    }
  }
}
