package co.assignment.master_detailapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


@Entity(tableName = "moviesListTable")
data class MoviesListTable(

    @SerializedName("cast")
    @TypeConverters(Converters::class)
    val cast: List<Movies>


) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}
