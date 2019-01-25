package com.example.renosyahputra.privatesceduler.res

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.TextView
import com.example.renosyahputra.privatesceduler.R
import com.example.renosyahputra.privatesceduler.obj.DateObj
import com.example.renosyahputra.privatesceduler.obj.TimeObj
import com.example.renosyahputra.privatesceduler.obj.UserSceduleDataObj

class CustomAlertDialogAddScedule {

    var context: Context
    val Newscedule : UserSceduleDataObj = UserSceduleDataObj()

    lateinit var dialog : AlertDialog

    constructor(context: Context) {
        this.context = context
    }

    var listener : MainActivityRes.OnNewScedule.OnNewSceduleAddListener? = null

    fun SetOnNewSceduleAddListener(listener: MainActivityRes.OnNewScedule.OnNewSceduleAddListener) {
        this.listener = listener
    }

    lateinit var dateObj: DateObj
    lateinit var timeObj: TimeObj

    fun SetDateAndTime(dateObj: DateObj,timeObj: TimeObj){
        this.dateObj = dateObj
        this.timeObj = timeObj
    }

    lateinit var textDate  :TextView
    lateinit var InputDes  :EditText

    fun InititationWidget(){
        dialog = AlertDialog.Builder(context)
                .create()
        val inflater = (context as Activity).layoutInflater
        val v = inflater.inflate(R.layout.custom_alert_dialog_add_scedule,null)

        dialog = AlertDialog.Builder(context)
                .setPositiveButton("Tambah",{ dialogInterface, i ->

                    Newscedule.UserTimeScedule = timeObj.cloneTime()
                    Newscedule.UserDateScedule = dateObj.cloneDate()
                    Newscedule.UserSceduleDescription = InputDes.text.toString()

                    if (listener!= null) {
                        listener!!.SceduleAdded(Newscedule)
                    }
                })
                .setNegativeButton("Batal", { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .create()

        textDate = v.findViewById(R.id.DateAndTimeToAddScedule)
        textDate.text = dateObj.toDateString() + " " + timeObj.toTimeString()

        InputDes = v.findViewById(R.id.SceduleDescriptionInput)


        dialog.setView(v)
        dialog.show()
    }
}