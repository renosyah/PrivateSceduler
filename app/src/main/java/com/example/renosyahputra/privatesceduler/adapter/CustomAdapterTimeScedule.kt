package com.example.renosyahputra.privatesceduler.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.renosyahputra.privatesceduler.R
import com.example.renosyahputra.privatesceduler.obj.SceduleTimeObj
import com.example.renosyahputra.privatesceduler.res.CustomAlertDialogAddScedule
import com.example.renosyahputra.privatesceduler.res.MainActivityRes

class CustomAdapterTimeScedule : RecyclerView.Adapter<CustomAdapterTimeScedule.Holder>{


    var context : Context
    var resources : Int = 0
    var objects : ArrayList<SceduleTimeObj>

    constructor(context: Context, resources: Int, objects: ArrayList<SceduleTimeObj>) : super() {
        this.context = context
        this.resources = resources
        this.objects = objects
    }

    var listener : MainActivityRes.OnNewScedule.OnNewSceduleAddListener? = null

    fun SetOnNewSceduleAddListener(listener: MainActivityRes.OnNewScedule.OnNewSceduleAddListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = (context as Activity).layoutInflater
        val v = inflater.inflate(resources,parent,false)

        return CustomAdapterTimeScedule.Holder(v)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = objects.get(position)
        holder.textViewSceduleDescription.text = item.SceduleDescription
        holder.ButtonAddTask.visibility = if (item.SceduleDescription == item.time.toTimeString()) View.VISIBLE else View.GONE

        holder.ButtonAddTask.setOnClickListener {
            val dialog = CustomAlertDialogAddScedule(context)
            dialog.SetDateAndTime(item.dateInSceduleTime,item.time)
            dialog.SetOnNewSceduleAddListener(listener!!)
            dialog.InititationWidget()
        }
    }

    class Holder : RecyclerView.ViewHolder {

        var textViewSceduleDescription : TextView
        var ButtonAddTask  :Button

        constructor(itemView: View) : super(itemView){
            this.textViewSceduleDescription =  itemView!!.findViewById(R.id.textViewSceduleDescription)
            this.ButtonAddTask = itemView.findViewById(R.id.ButtonAddNewScedule)
        }

    }
}