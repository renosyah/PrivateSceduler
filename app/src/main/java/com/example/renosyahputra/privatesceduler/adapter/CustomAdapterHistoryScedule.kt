package com.example.renosyahputra.privatesceduler.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.renosyahputra.privatesceduler.R
import com.example.renosyahputra.privatesceduler.obj.UserSceduleDataObj

class CustomAdapterHistoryScedule : RecyclerView.Adapter<CustomAdapterHistoryScedule.Holder> {

    var context : Context
    var resources : Int = 0
    var objects : ArrayList<UserSceduleDataObj>

    constructor(context: Context, resources: Int, objects: ArrayList<UserSceduleDataObj>) : super() {
        this.context = context
        this.resources = resources
        this.objects = objects
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
        holder.DateText.text = item.UserDateScedule.toDateString()
        holder.TimeText.text = item.UserTimeScedule.toTimeString()
        holder.DescText.text = item.UserSceduleDescription
    }

    class Holder : RecyclerView.ViewHolder {

        var TimeText  :TextView
        var DescText: TextView
        var DateText: TextView

        constructor(itemView: View) : super(itemView){
            this.TimeText =  itemView!!.findViewById(R.id.TimeInHistoryScedule)
            this.DescText = itemView.findViewById(R.id.DescHistoryScedule)
            this.DateText = itemView.findViewById(R.id.DateInHistoryScedule)
        }

    }
}