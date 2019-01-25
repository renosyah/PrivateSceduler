package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class MyMainSceduleData :Serializable {
    var DaySceduleListPerMonth = ArrayList<SceduleDayObj>()
    var UserSceduleList = ArrayList<UserSceduleDataObj>()
}