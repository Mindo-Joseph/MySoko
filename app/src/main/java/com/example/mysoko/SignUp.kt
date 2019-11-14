package com.example.mysoko
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        registerbtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            if (email.isEmpty()|| password.isEmpty()) return@setOnClickListener
            Toast.makeText(this,"Please fill in all the slots",Toast.LENGTH_SHORT).show()
            //Firebase Authentication
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener
                    //if successful
                    Toast.makeText(this,"Account created successfully!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Falied to create account "+{it.message},Toast.LENGTH_SHORT).show()
                }
        }
    }
}
