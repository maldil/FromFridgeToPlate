package com.malinda.fromfridgetoplate

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_food_item.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val foodList = arrayListOf("Chinees","Hamburger","Pizza","McDonalds")
    private val PREFS_FOOD = "Food"
    private val PRODUCT_TAG = "Food_PRODUCT"
    var food = ArrayList<Triple<String, String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Today_Plan.setOnClickListener {
            food = getDataFromSharedPreferences() as  ArrayList<Triple<String, String, String>>
            if (food.count()>3)
            {
                val random = Random()
                val randomFood = random.nextInt(food.count())
                val amount = food[randomFood].second.toDouble()/4
                if (amount>0)
                    selectedFoodText.text = food[randomFood].first + " " + amount +  food[randomFood].third
                val list = arrayListOf(0..food.count())
                val iterator = list.iterator()
                while(iterator.hasNext()){
                    val item = iterator.next()
                    if (item.contains(randomFood))
                        list.remove(item)
                }

                val random1 = Random()
                val randomFood1 = random1.nextInt(food.count())
                val amount1 = food[randomFood1].second.toDouble()/4
                if (amount1>0)
                    selectedFoodText2.text = food[randomFood1].first + " " + amount +  food[randomFood1].third

                val list1 = arrayListOf(0..food.count())
                val iterator1 = list1.iterator()
                while(iterator1.hasNext()){
                    val item = iterator1.next()
                    if (item.contains(randomFood))
                        list.remove(item)
                }


                val random2 = Random()
                val randomFood2 = random2.nextInt(food.count())
                val amount2 = food[randomFood2].second.toDouble()/4
                if (amount2>0)
                    selectedFoodText3.text = food[randomFood2].first + " " + amount +  food[randomFood2].third


            }
            else if(food.count()==3){
                val amount = food[0].second.toDouble()/4
                if (amount>0)
                    selectedFoodText.text = food[0].first + " " + amount +  food[0].third
                val amount1 = food[1].second.toDouble()/4
                if (amount1>0)
                    selectedFoodText2.text = food[1].first + " " + amount1 +  food[1].third
                val amount3 = food[2].second.toDouble()/4
                if (amount3>0)
                    selectedFoodText2.text = food[2].first + " " + amount1 +  food[2].third
            }
            else if (food.count()==2)
            {
                val amount = food[0].second.toDouble()/4
                if (amount>0)
                    selectedFoodText.text = food[0].first + " " + amount +  food[0].third
                val amount1 = food[1].second.toDouble()/4
                if (amount1>0)
                    selectedFoodText2.text = food[1].first + " " + amount1 +  food[1].third
            }
            else if (food.count()==1)
            {
                val amount = food[0].second.toDouble()/4
                if (amount>0)
                    selectedFoodText.text = food[0].first + " " + amount +  food[0].third
            }


            println (food)
        }

        Add_Schedule_Btn.setOnClickListener {
//            val newFood = addFoodtext.text.toString()
//            foodList.add(newFood)
//            addFoodtext.text.clear()
            val intent = Intent(this,DailySchedule::class.java)
            startActivity(intent)

        }

        add_food_btn.setOnClickListener{
            val intent = Intent(this,FoodStorage::class.java)
            startActivity(intent)
        }
    }


    private fun getDataFromSharedPreferences(): List<Triple<String, String, String>> {
        val gson = Gson()
        var productFromShared: List<Triple<String, String, String>> = ArrayList<Triple<String, String, String>>()
        val sharedPref = applicationContext.getSharedPreferences(PREFS_FOOD, Context.MODE_PRIVATE)
        val jsonPreferences = sharedPref.getString(PRODUCT_TAG, "")

        val type = object : TypeToken<List<Triple<String, String, String>>>() {

        }.getType()
        if (jsonPreferences !="")
            productFromShared = gson.fromJson(jsonPreferences, type)

        return productFromShared
    }
}
