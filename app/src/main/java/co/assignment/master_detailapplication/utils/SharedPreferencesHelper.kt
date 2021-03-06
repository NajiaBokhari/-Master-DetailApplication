package co.assignment.master_detailapplication.utils

import android.content.Context
import android.content.SharedPreferences
import co.assignment.master_detailapplication.data.Movies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object SharedPreferencesHelper {
    private const val NAME = "Movies"
    private const val MODE = Context.MODE_PRIVATE
    lateinit var preferences: SharedPreferences

    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)
    val SORTED_MOVIES_LIST_PREF = "sorted_movies_list"


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


    fun putList(list: MutableList<Movies>) {
        val listJson = Gson().toJson(list)
        preferences.edit {
            it.putString(SORTED_MOVIES_LIST_PREF, listJson)
        }
    }

    fun getList(): MutableList<Movies> {
        val listJson = preferences.getString(SORTED_MOVIES_LIST_PREF, "")
        if (!listJson.isNullOrBlank()) {
            val type = object : TypeToken<MutableList<Movies>>() {}.type
            return Gson().fromJson(listJson, type)
        }
        return mutableListOf()
    }
}