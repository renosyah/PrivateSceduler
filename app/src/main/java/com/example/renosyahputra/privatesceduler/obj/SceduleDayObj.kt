package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class SceduleDayObj()  : Serializable {
    var date : DateObj = DateObj()
    var sceduleTimeList = ArrayList<SceduleTimeObj>()
}