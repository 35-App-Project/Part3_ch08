package com.choi.part3_ch08.data.source.local

import androidx.room.TypeConverter
import com.choi.part3_ch08.util.DateUtil
import java.util.Date

class DateConverter {

    @TypeConverter
    fun toDate(timestamp:String?) : Date? {
        return timestamp?.let { DateUtil.dbDateFormat.parse(it) }
    }

    @TypeConverter
    fun toTimeStamp(date: Date?) : String? {
        return DateUtil.dbDateFormat.format(date)
    }

}