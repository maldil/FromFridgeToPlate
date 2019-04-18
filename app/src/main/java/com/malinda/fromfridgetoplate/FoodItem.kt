package com.malinda.fromfridgetoplate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_food_item.*
import android.text.Spanned
import android.text.InputFilter
import android.widget.EditText


class FoodItem : AppCompatActivity() {
    internal var foods = arrayOf("Bread","Pizza","Cake","Noodle","Tofu","Dim sum","Sour soup")
    internal var unit = arrayOf("Kg","l","g","pound","ounce","unit")
    internal var allowerd = "1234567890"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_item)

        val sp = findViewById(R.id.food_item_sppiner) as Spinner
        val adapter1 = ArrayAdapter(this,android.R.layout.simple_list_item_1,foods)
        sp.adapter = adapter1

        val sp1 = findViewById(R.id.food_item_spinner3) as Spinner
        val adapter2 = ArrayAdapter(this,android.R.layout.simple_list_item_1,unit)
        sp1.adapter = adapter2


        food_submit_item.setOnClickListener{
            val food = sp.getSelectedItem().toString()
            val unit = sp1.getSelectedItem().toString()
            var amount = submit_amount.text.toString()
            try {
                amount.toDouble()
            }catch (e:NumberFormatException)
            {
                amount = "0"
            }
            var j = 0
            val iterator = FoodStorage.food.iterator()
            while(iterator.hasNext()){
                val item = iterator.next()
                try {
                    var amount = item.second.toDouble()
                } catch (e: NumberFormatException) {
                    iterator.remove()
                }
            }
            FoodStorage.food.add(Triple(food,amount,unit))
            FoodStorage.setDataFromSharedPreferences(this,FoodStorage.food)

            val intent = Intent(this,FoodStorage::class.java)
            startActivity(intent)
        }
    }

    private val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source != null && !allowerd.contains("" + source)) {
            ""
        } else null
    }
}
