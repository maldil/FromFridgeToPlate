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

class DailySchedule : AppCompatActivity() {
    companion object {
        val schedule = ArrayList<Triple<String, String, String>>()
    }
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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
