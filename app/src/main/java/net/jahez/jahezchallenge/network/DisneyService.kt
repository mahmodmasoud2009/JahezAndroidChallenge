/*
 * Designed and developed by 2022 Mahmoud Masoud
 */

package net.jahez.jahezchallenge.network
import com.skydoves.sandwich.ApiResponse
import net.jahez.jahezchallenge.model.Entity
import retrofit2.http.GET

interface DisneyService {

  @GET("restaurants.json")
  suspend fun fetchDisneyPosterList(): ApiResponse<List<Entity>>
}
