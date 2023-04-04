package com.example.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.wishlist.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val button = findViewById<Button>(R.id.input)


        val fragment1: Fragment = listFragment()
        val fragment2: Fragment = StatFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {item->
            lateinit var fragment: Fragment
            when(item.itemId){
                R.id.list_view -> fragment = fragment1
                R.id.dailylist -> fragment = fragment2
            }
            replaceFragement(fragment)
            true
        }

        bottomNavigationView.selectedItemId = R.id.list_view

        button.setOnClickListener{
            val i = Intent(this, DetailActivity::class.java)
            startActivity(i)

        }



    }
    private fun replaceFragement(frag: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, frag)
        fragmentTransaction.commit()
    }
}