package co.assignment.master_detailapplication.networking.repository

import co.assignment.master_detailapplication.networking.BaseRepository
import co.assignment.master_detailapplication.networking.RetroNetwork
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.networking.models.PhotosSearchResponse
import co.assignment.master_detailapplication.networking.models.RetroApiResponse

object AuthRepository : BaseRepository(), AuthApi {

//     const val URL_PHOTO_SEARCH_API = "?method=flickr.photos.search&format=json&nojsoncallback=1&page=1&per_page=10&api_key=728e303f52d4ce6d77893a502295b2ea"
     const val URL_PHOTO_SEARCH_API = "?method=flickr.photos.search&format=json&nojsoncallback=1&page=1&per_page=10&api_key=728e303f52d4ce6d77893a502295b2ea&safe_search=1&safety_level=1"

    private val API: AuthRetroService = RetroNetwork.createService(AuthRetroService::class.java)

    override suspend fun getMovies(movieTitle:String): RetroApiResponse<PhotosSearchResponse> {
        val response = executeSafely(call = { API.getMovies(movieTitle) })
        when (response) {
            is RetroApiResponse.Success -> {
            }
        }
        return response
    }
}
