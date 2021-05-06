package co.yap.master_detailapplication.utils

import android.content.Context
import android.content.SharedPreferences
import co.yap.master_detailapplication.data.Movies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object SharedPreferencesHelper {
    private const val NAME = "SpinKotlin"
    private const val MODE = Context.MODE_PRIVATE
      lateinit var preferences: SharedPreferences

    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)
    val SORTED_MOVIES_LIST_PREF = "sorted_movies_list"

    //
    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

//    var sortedMoviesList: ArrayList<Movies>
//        get() = preferences.all(SORTED_MOVIES_LIST_PREF.first, SORTED_MOVIES_LIST_PREF.second)
//        set(value) = preferences.edit {
//            it.p(SORTED_MOVIES_LIST_PREF.first, value)
//        }
    //

//    fun <T> SharedPreferences.putList(spListKey: String, list: List<T>) {
//        val listJson = Gson().toJson(list)
//        edit {
//            it.putString(spListKey, listJson)
//        }
//    }

        fun putList(list: MutableList<Movies>) {
        val listJson = Gson().toJson(list)
         preferences.edit {
            it.putString(SORTED_MOVIES_LIST_PREF, listJson)
        }
    }

    //    inline fun <reified T> SharedPreferences.getList(spListKey: String): List<T> {
      fun  getList(): MutableList<Movies> {
        val listJson = preferences.getString(SORTED_MOVIES_LIST_PREF, "")
        if (!listJson.isNullOrBlank()) {
            val type = object : TypeToken<MutableList<Movies>>() {}.type
            return Gson().fromJson(listJson, type)
        }
        return mutableListOf()
    }
}