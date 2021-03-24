package co.yap.master_detailapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class Movies(

    @ColumnInfo(name = "username")
    var Username: String,

    @ColumnInfo(name = "password")
    var Password: String,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "year")
    var year: String = "",

    @ColumnInfo(name = "cast")
    var cast: String = "",
//    List<String>

    @ColumnInfo(name = "genre")
    var genre: String = "",
//List<String>

    @ColumnInfo(name = "poster")
    var poster: String = "",

    @ColumnInfo(name = "rating")
    var rating: Double = 0.0

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}