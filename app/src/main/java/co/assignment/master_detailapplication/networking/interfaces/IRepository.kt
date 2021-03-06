package co.assignment.master_detailapplication.networking.interfaces

  import co.assignment.master_detailapplication.networking.models.ApiResponse
  import co.assignment.master_detailapplication.networking.models.RetroApiResponse
  import retrofit2.Response

internal interface IRepository {
    suspend fun <T : ApiResponse> executeSafely(call: suspend () -> Response<T>): RetroApiResponse<T>
}