package co.assignment.master_detailapplication.networking.repository

 import co.assignment.master_detailapplication.networking.models.PhotosSearchResponse
import co.assignment.master_detailapplication.networking.models.RetroApiResponse

interface AuthApi {
     suspend fun getMovies(movieTitle:String): RetroApiResponse<PhotosSearchResponse>
}