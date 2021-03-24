package co.yap.master_detailapplication.networking

import co.yap.master_detailapplication.networking.interfaces.IRepository
import co.yap.master_detailapplication.networking.models.ApiError
import co.yap.master_detailapplication.networking.models.ApiResponse
import co.yap.master_detailapplication.networking.models.RetroApiResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import com.google.gson.stream.MalformedJsonException as MalformedJsonException1

const val MALFORMED_JSON_EXCEPTION_CODE = 0

abstract class BaseRepository : IRepository {

    override suspend fun <T : ApiResponse> executeSafely(call: suspend () -> Response<T>): RetroApiResponse<T> {
        try {
            val response: Response<T> = call.invoke()
            if (response.isSuccessful) {
                return RetroApiResponse.Success(response.code(), response.body()!!)
            }

            return RetroApiResponse.Error(detectError(response))

        } catch (exception: MalformedJsonException1) {
            return RetroApiResponse.Error(
                ApiError(
                    MALFORMED_JSON_EXCEPTION_CODE,
                    exception.localizedMessage
                )
            )
        } catch (exception: Exception) {
            return RetroApiResponse.Error(ApiError(0, exception.localizedMessage))
        }
    }

    private fun <T : ApiResponse> detectError(response: Response<T>): ApiError {
        if (response.code() == 504) {
            return ApiError(response.code(), "")
        }

        val error: String? = response.errorBody()!!.string()
        return ApiError(
            response.code(),
            fetchErrorFromBody(error) ?: error ?: "Something went wrong"
        )
    }

    private fun fetchErrorFromBody(response: String?): String? {
        response?.let {
            if (it.isNotBlank()) {
                try {
                    val obj = JSONObject(it)

                    if (obj.has("errors")) {
                        val errors = obj.getJSONArray("errors")
                        if (errors.length() > 0) {
                            val message = errors.getJSONObject(0).getString("message")
                            return if (message != "null") {
                                errors.getJSONObject(0).getString("message")
                            } else {
                                "Something went wrong"
                            }
                        }
                    } else if (obj.has("error")) {
                        val error = obj.getString("error") ?: ""
                        if (error.contains("unauthorized")) {
                            return ""
                        }
                    }


                } catch (e: JSONException) {
                }
            }
        }
        return null
    }
}