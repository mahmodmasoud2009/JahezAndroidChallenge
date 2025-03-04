/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.jahez.jahezchallenge.network.JahezService
import net.jahez.jahezchallenge.persistence.JahezDao
import net.jahez.jahezchallenge.ui.main.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
      disneyService: JahezService,
      posterDao: JahezDao
  ): MainRepository {
    return MainRepository(disneyService, posterDao)
  }
}
