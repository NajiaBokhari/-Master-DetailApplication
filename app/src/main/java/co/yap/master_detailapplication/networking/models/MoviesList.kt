package co.yap.master_detailapplication.networking.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesList(
    val data: ArrayList<Movie>
) : ApiResponse(), Parcelable