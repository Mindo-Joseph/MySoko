package com.example.mysoko

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Button

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_create_ad.*

class create_ad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ad)

        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val nav_servies = Intent(this@create_ad, MainActivity::class.java)
                    startActivity(nav_servies)
                    finish()
                }

                R.id.navigation_services -> {
                    val nav_create = Intent(this@create_ad, search_service::class.java)
                    startActivity(nav_create)
                    finish()
                }
            }
            false
        }

        selectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }


    }

    var selectedUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val selectedUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            selectImage.setBackgroundDrawable(bitmapDrawable)


        }
    }


}


