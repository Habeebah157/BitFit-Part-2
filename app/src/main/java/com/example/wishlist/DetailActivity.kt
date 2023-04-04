package com.example.wishlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailactivity)

        val record = findViewById<Button>(R.id.record)
        record.setOnClickListener{
            val food = findViewById<EditText>(R.id.food1).text.toString()
            val calories = findViewById<EditText>(R.id.calories2).text.toString()
            lifecycleScope.launch(Dispatchers.IO){
                (application as MyApplication).db.FitBitDao().insert(
                    Fitbitentity(food, calories)
                )


            }
            val i = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(i)


        }



    }
}