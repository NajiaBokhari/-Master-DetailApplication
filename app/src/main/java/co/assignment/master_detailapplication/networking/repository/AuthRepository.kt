package co.assignment.master_detailapplication.networking.repository

import co.assignment.master_detailapplication.networking.BaseRepository
import co.assignment.master_detailapplication.networking.RetroNetwork
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.networking.models.RetroApiResponse

object AuthRepository : BaseRepository(), AuthApi {

    const val URL_GET_MOVIES = "/api/movies"

    private val API: AuthRetroService = RetroNetwork.createService(AuthRetroService::class.java)

    override suspend fun getMovies(): RetroApiResponse<MoviesList> {
        val response = executeSafely(call = { API.getMovies() })
        when (response) {
            is RetroApiResponse.Success -> {
            }
        }
        return response
    }
}
