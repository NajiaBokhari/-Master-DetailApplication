package co.assignment.master_detailapplication.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity(tableName = "movies")
data class Movies(

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "year")
    var year: Int ,

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

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

 }

