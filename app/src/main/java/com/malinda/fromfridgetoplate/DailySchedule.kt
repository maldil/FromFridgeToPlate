package com.malinda.fromfridgetoplate

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class DailySchedule : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_schedule)

        val listView = findViewById<ListView>(R.id.DailySchedule_schedulelist)
        val color = Color.parseColor("white")
        listView.setBackgroundColor(color)
        listView.adapter = MyCustomAdapter(this)


    }
    private class MyCustomAdapter(context: Context): BaseAdapter(){
        private val mContext:Context

        init {
            mContext = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layout = LayoutInflater.from(mContext)
            val row_schedule = layout.inflate(R.layout.daily_schedule_row,parent,false)
            return row_schedule
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return 10
        }

    }
}
