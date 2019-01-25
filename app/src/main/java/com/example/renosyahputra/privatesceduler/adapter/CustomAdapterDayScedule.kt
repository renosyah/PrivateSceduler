package com.example.renosyahputra.privatesceduler.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.renosyahputra.privatesceduler.R
import com.example.renosyahputra.privatesceduler.obj.SceduleDayObj
import com.example.renosyahputra.privatesceduler.res.MainActivityRes


class CustomAdapterDayScedule : RecyclerView.Adapter<CustomAdapterDayScedule.Holder> {

    var context : Context
    var resources : Int = 0
    var objects : ArrayList<SceduleDayObj>

    constructor(context: Context, resources: Int, objects: ArrayList<SceduleDayObj>) : super() {
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

        return Holder(v)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = objects.get(position)
        holder.DaySceduleText.text = "Day ${item.date.Day}"
        holder.RecycleviewTimeScedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = CustomAdapterTimeScedule(context,R.layout.custom_adapter_time_scedule,item.sceduleTimeList)
        adapter.SetOnNewSceduleAddListener(listener!!)

        holder.RecycleviewTimeScedule.adapter = adapter

    }

    class Holder : RecyclerView.ViewHolder {

        var DaySceduleText : TextView
        var RecycleviewTimeScedule : RecyclerView

        constructor(itemView: View) : super(itemView){
            this.DaySceduleText =  itemView.findViewById(R.id.DaySceduleAdapter)
            this.RecycleviewTimeScedule = itemView.findViewById(R.id.ListTimeScedule)
        }

    }
}