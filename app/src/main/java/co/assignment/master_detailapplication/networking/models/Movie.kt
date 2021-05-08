package co.assignment.master_detailapplication.networking.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    var title: String?= null,
    var year: Int?= null,
    var cast: List<String>?= null,
    var genre: List<String>?= null,
    var poster: String ?= null,
    var rating: Float ?= null
) : Parcelable

