package co.yap.master_detailapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Movies::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class MoviesDatabase : RoomDatabase() {

    abstract fun daOmovies(): DAOmovies
    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDataseClient(context: Context): MoviesDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MoviesDatabase::class.java, "movies_db")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}