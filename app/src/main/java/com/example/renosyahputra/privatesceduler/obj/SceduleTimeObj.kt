package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class SceduleTimeObj :Serializable {
    var time : TimeObj = TimeObj()
    var dateInSceduleTime : DateObj = DateObj()
    var SceduleDescription = ""
}