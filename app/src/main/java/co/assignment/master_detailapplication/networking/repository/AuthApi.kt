package co.assignment.master_detailapplication.networking.repository

import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.networking.models.RetroApiResponse

interface AuthApi {
    suspend fun getMovies(): RetroApiResponse<MoviesList>
}