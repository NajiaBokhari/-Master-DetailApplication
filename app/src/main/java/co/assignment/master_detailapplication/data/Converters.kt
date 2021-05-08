package co.assignment.master_detailapplication.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun gettingListFromString(castsNames: String): List<String> {
        val list = mutableListOf<String>()

        val array = castsNames.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s)
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<String>): String {
        var castsNames=""
        for (i in list) castsNames += ",$i"
        return castsNames
    }
}