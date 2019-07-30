package example.room.livedata.utilis

import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtilis {
    companion object{
        @JvmStatic
        fun getFormattedString(ms:Long):String{
            try{
                val date = Date(ms)
                val format = SimpleDateFormat("E, dd/MM/yyyy hh:mm:ss a")
                return format.format(date)
            }catch (ex:Exception){
                return "Invalid Date"
            }

        }
    }
}