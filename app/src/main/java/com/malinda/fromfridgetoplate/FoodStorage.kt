package com.malinda.fromfridgetoplate

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_food_storage.*
import java.util.ArrayList

class FoodStorage : AppCompatActivity() {
    companion object {
        var food = ArrayList<Triple<String, String, String>>()

        private val PREFS_FOOD = "Food"
        private val PRODUCT_TAG = "Food_PRODUCT"

        public final fun setDataFromSharedPreferences(ctxt:Context,curProduct: ArrayList<Triple<String, String, String>>) {

            val gson = Gson()
            val jsonCurProduct = gson.toJson(curProduct)

            val sharedPref = ctxt.getSharedPreferences(PREFS_FOOD, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(PRODUCT_TAG, jsonCurProduct)
            editor.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_storage)
        food  = getDataFromSharedPreferences() as ArrayList<Triple<String, String, String>>

        food_storage_home.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        button_add_food.setOnClickListener{
            val intent = Intent(this,FoodItem::class.java)
            startActivity(intent)
        }

        val listView = findViewById<ListView>(R.id.food_list_view)
        val color = Color.parseColor("white")
        listView.setBackgroundColor(color)
        listView.adapter = MyCustomAdapter(this, FoodStorage.food)

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
    private class MyCustomAdapter(context: Context, schedule:ArrayList<Triple<String,String,String>>): BaseAdapter(){
        private val mContext: Context
        private val mSchedule:ArrayList<Triple<String,String,String>>
        init {
            mContext = context
            mSchedule = schedule
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layout = LayoutInflater.from(mContext)
            val row_schedule = layout.inflate(R.layout.food_item_row,parent,false)
            val food = row_schedule.findViewById<TextView>(R.id.fodd_item_food_enter)
            val amount = row_schedule.findViewById<TextView>(R.id.food_item_food_amount)
            food.text = mSchedule[position].first
            amount.text = mSchedule[position].second + mSchedule[position].third
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
