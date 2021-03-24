package co.yap.master_detailapplication.networking.models

sealed class RetroApiResponse<out T: ApiResponse> {
    data class Success<out T : ApiResponse>(val code: Int, val data: T) : RetroApiResponse<T>()
    data class Error(val error: ApiError) : RetroApiResponse<Nothing>()
}