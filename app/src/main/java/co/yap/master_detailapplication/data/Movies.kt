package co.yap.master_detailapplication.data

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies")
data class Movies(

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "year")
    var year: String = "",

    @SerializedName("cast")
    @TypeConverters(Converters::class)
    val cast: List<String>,

    @SerializedName("genre")
    @TypeConverters(Converters::class)
    val genre: List<String>,

    @ColumnInfo(name = "poster")
    var poster: String = "",

    @ColumnInfo(name = "rating")
    var rating: Float

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

 }
