package com.example.renosyahputra.privatesceduler.obj

import java.io.Serializable

class DateObj : Serializable {
    var Day = 0
    var Month = 0
    var Year = 0

    constructor(Day: Int, Month: Int, Year: Int) {
        this.Day = Day
        this.Month = Month
        this.Year = Year
    }
    constructor()

    fun Compare(dateToCompare : DateObj) :Boolean{
        return this.Day == dateToCompare.Day && this.Month == dateToCompare.Month && this.Year == dateToCompare.Year
    }

    fun toDateString() : String {
        return "${this.Day}/${this.Month}/${this.Year}"
    }

    fun cloneDate() : DateObj {
        return DateObj(this.Day,this.Month,this.Year)
    }
}