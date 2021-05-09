package co.assignment.master_detailapplication.networking.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosSearchResponse(
    val photos: PhotosMetaData
): ApiResponse(), Parcelable

@Parcelize
data class PhotosMetaData(
    val page: Int,
    val photo: List<PhotoResponse>
): Parcelable

@Parcelize
data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
):Parcelable
