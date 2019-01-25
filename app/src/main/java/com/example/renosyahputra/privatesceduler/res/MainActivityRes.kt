package com.example.renosyahputra.privatesceduler.res

import com.example.renosyahputra.privatesceduler.obj.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivityRes {

    public class OnNewScedule{
        interface OnNewSceduleAddListener {
            fun SceduleAdded(scedule : UserSceduleDataObj)
        }
    }

    companion object {

        fun SetDataToBeEmpty(dateObj: DateObj,mainSceduleData: MyMainSceduleData){

            mainSceduleData.DaySceduleListPerMonth.clear()

            val cal = Calendar.getInstance()

            cal.set(Calendar.DAY_OF_MONTH, 1)
            cal.set(Calendar.MONTH,dateObj.Month - 1)
            cal.set(Calendar.YEAR,dateObj.Year)


            while ((dateObj.Month - 1) == cal.get(Calendar.MONTH)) {
                val day = SceduleDayObj()
                day.date.Day = cal.get(Calendar.DAY_OF_MONTH)
                day.date.Month = dateObj.Month
                day.date.Year = dateObj.Year


                    for (timeDay in 1..24) {
                        for (minuteHour in 0..45 step 15) {
                            val time = SceduleTimeObj()
                            time.dateInSceduleTime = day.date.cloneDate()
                            time.time.Minute = minuteHour
                            time.time.Hour = timeDay
                            time.SceduleDescription = time.time.toTimeString()
                            day.sceduleTimeList.add(time)
                        }
                }

                mainSceduleData.DaySceduleListPerMonth.add(day)
                cal.add(Calendar.DAY_OF_MONTH, 1)
            }
            cal.clear()
        }


        fun SetEmptyDataFillWithMyOwnScedule(mainSceduleData: MyMainSceduleData){
            for (userScedule in mainSceduleData.UserSceduleList){
                for (day in mainSceduleData.DaySceduleListPerMonth){
                    for (dataTarget in day.sceduleTimeList) {
                        if (dataTarget.time.Compare(userScedule.UserTimeScedule) && day.date.Compare(userScedule.UserDateScedule)) {
                            dataTarget.SceduleDescription = userScedule.UserSceduleDescription  +"\n ${userScedule.UserTimeScedule.toTimeString()}"
                        }

                    }
                }
            }
        }

        fun CompareWithPresentDate(dateObj: DateObj,timeObj: TimeObj):Boolean{
            val cal = Calendar.getInstance()
            val dateNow = DateObj(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH)+1,cal.get(Calendar.YEAR))
            val timeNow = TimeObj(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE))

            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val tgl1 = sdf.parse(dateObj.toDateString()+" "+timeObj.toTimeString())
            val tgl2 = sdf.parse(dateNow.toDateString()+" "+timeNow.toTimeString())
            return (tgl2 < tgl1)
        }

    }
}