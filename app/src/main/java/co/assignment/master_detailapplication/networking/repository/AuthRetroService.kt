package co.assignment.master_detailapplication.networking.repository

import co.assignment.master_detailapplication.networking.models.PhotosSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthRetroService {

    @GET(AuthRepository.URL_PHOTO_SEARCH_API)
//    suspend fun getMovies(): Response<PhotosSearchResponse>
//    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&page=1&per_page=10&api_key=728e303f52d4ce6d77893a502295b2ea")
    suspend fun getMovies(@Query(value = "text") movie_title: String): Response<PhotosSearchResponse>

}