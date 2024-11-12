package alidoran.android.room.dao

import alidoran.android.room.model.Contact
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RoomConverters {
    //for date and time conversions
    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

    //list of custom object in your database
    @TypeConverter
    fun saveAddressList(listOfString: List<Contact?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun getAddressList(listOfString: String?): List<Contact?>? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<List<String?>?>() {}.type
        )
    }

    /*  for converting List<Double?>?  you can do same with other data type*/
    @TypeConverter
    fun saveDoubleList(listOfString: List<Double>): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun getDoubleList(listOfString: List<Double>): List<Double> {
        return Gson().fromJson(
            listOfString.toString(),
            object : TypeToken<List<Double?>?>() {}.type
        )
    }

//    // for converting the json object or String into Pojo or DTO class
//    @TypeConverter
//    fun toCurrentLocationDTO(value: String?): CurrentLocationDTO {
//        return  Gson().fromJson(
//            value,
//            object : TypeToken<CurrentLocationDTO?>() {}.type
//        )
//    }
//
//    @TypeConverter
//    fun fromCurrentLocationDTO(categories: CurrentLocationDTO?): String {
//        return Gson().toJson(categories)
//
//    }

}