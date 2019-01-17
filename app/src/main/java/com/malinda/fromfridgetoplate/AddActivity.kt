package com.malinda.fromfridgetoplate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    internal var days = arrayOf("Monday","Tuesday","Wendsday","Thursday","Friday","Saturday","Sunday")
    internal var activites = arrayOf("Runing","Meeting","Sleeping","Talking","Typing")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val sp = findViewById(R.id.spinner_day) as Spinner
        val adapter1 = ArrayAdapter(this,android.R.layout.simple_list_item_1,days)
        sp.adapter = adapter1


        val sp2 = findViewById(R.id.spinner_activity) as Spinner
        val adapter2 = ArrayAdapter(this,android.R.layout.simple_list_item_1,activites)
        sp2.adapter = adapter2

        activity_submit.setOnClickListener {
            val day = sp.getSelectedItem().toString();
            val activ = sp2.getSelectedItem().toString()
            val duration = auto_duration.text.toString()
            DailySchedule.schedule.add(Triple(day,activ,duration))
            val intent = Intent(this,DailySchedule::class.java)
            startActivity(intent)
        }

    }
}
