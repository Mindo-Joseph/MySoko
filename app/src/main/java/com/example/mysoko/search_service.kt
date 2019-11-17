package com.example.mysoko

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

import java.util.ArrayList

class search_service : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_service)


        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val nav_servies = Intent(this@search_service, MainActivity::class.java)
                    startActivity(nav_servies)
                    finish()
                }

                R.id.navigation_create -> {
                    val nav_create = Intent(this@search_service, create_ad::class.java)
                    startActivity(nav_create)
                    finish()
                }
            }
            false
        }

    }


}









