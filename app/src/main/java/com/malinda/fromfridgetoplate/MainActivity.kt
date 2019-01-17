package com.malinda.fromfridgetoplate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val foodList = arrayListOf("Chinees","Hamburger","Pizza","McDonalds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Today_Plan.setOnClickListener {
            val random = Random()
            val randomFood = random.nextInt(foodList.count())
            selectedFoodText.text = foodList[randomFood]

        }

        Add_Schedule_Btn.setOnClickListener {
//            val newFood = addFoodtext.text.toString()
//            foodList.add(newFood)
//            addFoodtext.text.clear()
            val intent = Intent(this,DailySchedule::class.java)
            startActivity(intent)

        }
    }
}
