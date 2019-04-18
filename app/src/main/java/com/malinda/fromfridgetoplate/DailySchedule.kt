package com.malinda.fromfridgetoplate

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_daily_schedule.*
import java.util.ArrayList
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DailySchedule : AppCompatActivity() {

    companion object {
        var schedule = ArrayList<Triple<String, String, String>>()
        private val PREFS_TAG = "SharedPrefs"
        private val PRODUCT_TAG = "MyProduct"

        public final fun setDataFromSharedPreferences(ctxt:Context,curProduct: ArrayList<Triple<String, String, String>>) {

            val gson = Gson()
            val jsonCurProduct = gson.toJson(curProduct)

            val sharedPref = ctxt.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString(PRODUCT_TAG, jsonCurProduct)
            editor.commit()
        }
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schedule = getDataFromSharedPreferences() as ArrayList<Triple<String, String, String>>

//        schedule.add(Triple("Monday","Running","4.5"))
//        schedule.add(Triple("TuesDay","Running","2.5"))
//        schedule.add(Triple("Wendsday","Running","4.5"))
//        schedule.add(Triple("Thursday","Running","3.5"))
//        schedule.add(Triple("Friday","Running","4.5"))
//        schedule.add(Triple("Monday","Climbing","5.5"))
//        schedule.add(Triple("Tuesday","Running","4.5"))
//        schedule.add(Triple("Wendsday","Eating","3.5"))


        setContentView(R.layout.activity_daily_schedule)

        val listView = findViewById<ListView>(R.id.DailySchedule_schedulelist)
        val color = Color.parseColor("white")
        listView.setBackgroundColor(color)
        listView.adapter = MyCustomAdapter(this,schedule)

        btn_add_activity.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }

        daily_schedule_home.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }



    private fun getDataFromSharedPreferences(): List<Triple<String, String, String>> {
        val gson = Gson()
        var productFromShared: List<Triple<String, String, String>> = ArrayList<Triple<String, String, String>>()
        val sharedPref = applicationContext.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)
        val jsonPreferences = sharedPref.getString(PRODUCT_TAG, "")

        val type = object : TypeToken<List<Triple<String, String, String>>>() {

        }.getType()
        if (jsonPreferences !="")
            productFromShared = gson.fromJson(jsonPreferences, type)

        return productFromShared
    }


    private class MyCustomAdapter(context: Context,schedule:ArrayList<Triple<String,String,String>>): BaseAdapter(){
        private val mContext:Context
        private val mSchedule:ArrayList<Triple<String,String,String>>
        init {
            mContext = context
            mSchedule = schedule
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layout = LayoutInflater.from(mContext)
            val row_schedule = layout.inflate(R.layout.daily_schedule_row,parent,false)
            val activity_view = row_schedule.findViewById<TextView>(R.id.DailySchedule_Activity_Enter)
            val day_view = row_schedule.findViewById<TextView>(R.id.DailySchedule_Row_Day_Enter)
            val duration_view = row_schedule.findViewById<TextView>(R.id.Daily_Scheduler_Duration_Enter)
            activity_view.text = mSchedule[position].second
            day_view.text = mSchedule[position].first
            duration_view.text = mSchedule[position].third
            return row_schedule
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {

            return mSchedule.size
        }

    }


}
