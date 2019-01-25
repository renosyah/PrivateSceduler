package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class TimeObj : Serializable {
    var Hour = 0
    var Minute = 0



    constructor(Hour: Int, Minute: Int) {
        this.Hour = Hour
        this.Minute = Minute
    }

    constructor()


    fun Compare(timeToCompare : TimeObj)  :Boolean{
        return this.Hour == timeToCompare.Hour && this.Minute == timeToCompare.Minute
    }

    fun toTimeString() : String {
        return "${this.Hour}:${this.Minute}"
    }

    fun cloneTime() : TimeObj{
        return TimeObj(this.Hour,this.Minute)
    }
}