package co.yap.master_detailapplication.networking.repository

 import co.yap.master_detailapplication.networking.models.MoviesList
 import retrofit2.Response
import retrofit2.http.GET

interface AuthRetroService {

    @GET(AuthRepository.URL_GET_MOVIES)
    suspend fun getMovies(): Response<MoviesList>

}