package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class UserSceduleDataObj : Serializable {
    var UserDateScedule : DateObj = DateObj()
    var UserTimeScedule : TimeObj = TimeObj()
    var UserSceduleDescription = ""
}