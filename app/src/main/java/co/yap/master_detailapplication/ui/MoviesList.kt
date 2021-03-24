package co.yap.master_detailapplication.ui


import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("movies")
    var movies: List<Movy>
) {
    data class Movy(
        @SerializedName("cast")
        var cast: List<String>,
        @SerializedName("genres")
        var genres: List<String>,
        @SerializedName("rating")
        var rating: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("year")
        var year: Int
    )
}