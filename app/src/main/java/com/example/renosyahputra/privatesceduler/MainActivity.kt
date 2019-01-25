package com.example.renosyahputra.privatesceduler

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import com.example.renosyahputra.privatesceduler.adapter.CustomAdapterDayScedule
import com.example.renosyahputra.privatesceduler.adapter.CustomAdapterHistoryScedule
import com.example.renosyahputra.privatesceduler.obj.DateObj
import com.example.renosyahputra.privatesceduler.obj.MyMainSceduleData
import com.example.renosyahputra.privatesceduler.obj.UserSceduleDataObj
import com.example.renosyahputra.privatesceduler.res.MainActivityRes
import com.example.renosyahputra.privatesceduler.res.MainActivityRes.Companion.CompareWithPresentDate
import com.example.renosyahputra.privatesceduler.storage.SerializableSave
import com.gmail.syahputrareno975.customdatepickerlib.CustomDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        MainActivityRes.OnNewScedule.OnNewSceduleAddListener,
        CustomDatePicker.OnDateChooseListener {


    lateinit var context: Context
    lateinit var mainData : MyMainSceduleData
    val dateChoosed  :DateObj = DateObj(
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            Calendar.getInstance().get(Calendar.MONTH)+1,
            Calendar.getInstance().get(Calendar.YEAR)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InititationWidget()
    }

    private fun InititationWidget(){
        context = this@MainActivity

        mainData = if (SerializableSave(context,SerializableSave.userScedulerSessionName).load() != null)
            SerializableSave(context,SerializableSave.userScedulerSessionName).load() as MyMainSceduleData else
            MyMainSceduleData()

        ChooseMontAndYears.text = dateChoosed.toDateString()

        MainActivityRes.SetDataToBeEmpty(dateChoosed,mainData)
        MainActivityRes.SetEmptyDataFillWithMyOwnScedule(mainData)

        SetAdapterMainScedule(mainData)

        MainBottomnavigation.setOnNavigationItemSelectedListener(this)
        ChooseMontAndYears.setOnClickListener(this)
    }


    fun SetAdapterMainScedule(mainSceduleData : MyMainSceduleData){

        ListDayScedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = CustomAdapterDayScedule(context,R.layout.custom_adapter_day_scedule,mainSceduleData.DaySceduleListPerMonth)
        adapter.SetOnNewSceduleAddListener(this)

        ListDayScedule.adapter = adapter
    }

    fun SetAdapterFutureScedule(mainSceduleData : MyMainSceduleData){
        val dataAdapter = ArrayList<UserSceduleDataObj>()
        for (scedule in mainSceduleData.UserSceduleList.sortedWith(
                compareBy(
                        { it.UserDateScedule.Year },
                        {it.UserDateScedule.Month},
                        {it.UserDateScedule.Day},
                        {it.UserTimeScedule.Hour},
                        {it.UserTimeScedule.Minute})
        )){

            if (CompareWithPresentDate(scedule.UserDateScedule,scedule.UserTimeScedule)){
                dataAdapter.add(scedule)
            }
        }


        ListDayScedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CustomAdapterHistoryScedule(context,R.layout.custom_adapter_history_scedule,dataAdapter)

        ListDayScedule.adapter = adapter
    }

    fun SetAdapterHistoryScedule(mainSceduleData : MyMainSceduleData){

        val dataAdapter = ArrayList<UserSceduleDataObj>()
        for (scedule in mainSceduleData.UserSceduleList.sortedWith(
                compareBy(
                        { it.UserDateScedule.Year },
                        {it.UserDateScedule.Month},
                        {it.UserDateScedule.Day},
                        {it.UserTimeScedule.Hour},
                        {it.UserTimeScedule.Minute})
        )){

            if (!CompareWithPresentDate(scedule.UserDateScedule,scedule.UserTimeScedule)){
                dataAdapter.add(scedule)
            }
        }

        ListDayScedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CustomAdapterHistoryScedule(context,R.layout.custom_adapter_history_scedule,dataAdapter)

        ListDayScedule.adapter = adapter
    }


    override fun onClick(p0: View?) {
        when (p0){
            ChooseMontAndYears -> {
                val datePicker = CustomDatePicker(context)
                datePicker.setOnDateChooseListener(this)
                datePicker.OpenDialog()
            }
        }
    }
    override fun ChoosedDate(day: Int, month: Int, year: Int) {
        dateChoosed.Day = day
        dateChoosed.Month = month
        dateChoosed.Year = year

        ChooseMontAndYears.text = dateChoosed.toDateString()

        MainActivityRes.SetDataToBeEmpty(dateChoosed,mainData)
        MainActivityRes.SetEmptyDataFillWithMyOwnScedule(mainData)

        SetAdapterMainScedule(mainData)
    }


    override fun SceduleAdded(scedule: UserSceduleDataObj) {
        mainData.UserSceduleList.add(scedule)

        MainActivityRes.SetDataToBeEmpty(dateChoosed,mainData)
        MainActivityRes.SetEmptyDataFillWithMyOwnScedule(mainData)

        SetAdapterMainScedule(mainData)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_Scedule -> {
                ChooseMontAndYears.text = dateChoosed.toDateString()
                SetAdapterMainScedule(mainData)
            }
            R.id.navigation_future_scedule -> {
                ChooseMontAndYears.text = "Future Plan"
                SetAdapterFutureScedule(mainData)

            }
            R.id.navigation_past_scedule -> {
                ChooseMontAndYears.text = "History Plan"
                SetAdapterHistoryScedule(mainData)

            }
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed()
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onBackPressed() {
        SerializableSave(context,SerializableSave.userScedulerSessionName).save(mainData)
        super.onBackPressed()
    }
}
