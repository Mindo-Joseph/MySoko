package com.example.mysoko

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createtext.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }





        loginbtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            //Firebase Auth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener
                    //If successful
                    Toast.makeText(this,"Welcome Back",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                }
                .addOnFailureListener {
                    Toast.makeText(this,"Falied to sign in "+{it.message},Toast.LENGTH_SHORT).show()

                }

        }

    }
}
