package dk.shantech.composeoffers.extension

import android.icu.util.Calendar
import java.util.*

fun Date.isDateInThePast() : Boolean {
    return this.after(Calendar.getInstance().time)
}

fun Date.getWeekDay() : String {
     val cal = Calendar.getInstance()
    cal.time = this
    return when(cal.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "mandag"
        Calendar.TUESDAY -> "tirsdag"
        Calendar.WEDNESDAY -> "onsdag"
        Calendar.THURSDAY -> "torsdag"
        Calendar.FRIDAY -> "fredag"
        Calendar.SATURDAY -> "lørdag"
        Calendar.SUNDAY -> "søndag"
        else -> ""
    }
}