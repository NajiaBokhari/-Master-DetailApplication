package co.yap.master_detailapplication.data

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(Movies::class), version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDataseClient(context: Context) : MoviesDatabase {

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