package com.example.mysoko

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // Creating DatabaseReference.
    internal lateinit var databaseReference: DatabaseReference

    // Creating RecyclerView.
    internal lateinit var recyclerView: RecyclerView

    // Creating RecyclerView.Adapter.
    internal lateinit var adapter: RecyclerView.Adapter<*>

    // Creating Progress dialog
    internal lateinit var progressDialog: ProgressDialog

    // Creating List of ImageUploadInfo class.
    internal var list: MutableList<ImageUploadInfo> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign id to RecyclerView.
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        // Setting RecyclerView size true.
        //recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        // Assign activity this to progress dialog.
        progressDialog = ProgressDialog(this@MainActivity)

        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Images From Firebase.")

        // Showing progress dialog.
        progressDialog.show()

        // Setting up Firebase image upload folder path in databaseReference.
        // The path is already defined in MainActivity.

        databaseReference = FirebaseDatabase.getInstance().getReference(MainActivity.Database_Path)

        // Adding Add Value Event Listener to databaseReference.
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapshot in snapshot.children) {

                    val imageUploadInfo = postSnapshot.getValue(ImageUploadInfo::class.java)

                    if (imageUploadInfo != null) {
                        list.add(imageUploadInfo)
                    }
                }

                adapter = RecyclerViewAdapter(applicationContext, list)

                recyclerView.adapter = adapter

                // Hiding the progress dialog.
                progressDialog.dismiss()
            }

            override fun onCancelled(databaseError: DatabaseError) {

                // Hiding the progress dialog.
                progressDialog.dismiss()

            }
        })

        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_services -> {
                    val nav_servies = Intent(this@MainActivity, search_service::class.java)
                    startActivity(nav_servies)
                    finish()
                }

                R.id.navigation_create -> {
                    val nav_create = Intent(this@MainActivity, create_ad::class.java)
                    startActivity(nav_create)
                    finish()
                }
            }
            false
        }

    }

    companion object {
        val Database_Path = "All_Image_Uploads_Database"
    }

}
