package co.yap.master_detailapplication.networking.repository

import co.yap.master_detailapplication.networking.models.MoviesList
import co.yap.master_detailapplication.networking.models.RetroApiResponse

interface AuthApi {
    suspend fun getMovies(): RetroApiResponse<MoviesList>
}