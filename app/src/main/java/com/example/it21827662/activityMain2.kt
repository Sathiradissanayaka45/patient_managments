package com.example.it21827662

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.it21827662.database.UserRepository
import com.example.it21827662.database.User_database
import com.example.it21827662.database.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activityMain2 : AppCompatActivity() {

    lateinit var logEmail: EditText
    lateinit var pwd : EditText
    lateinit var loginbtn : Button
    lateinit var signupbtn : Button
    lateinit var userdetails : Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val userRepo = UserRepository(User_database.getInstance(this))
        logEmail = findViewById(R.id.userEmail)
        pwd = findViewById(R.id.signpwd)
        loginbtn = findViewById(R.id.loginbtn)
        signupbtn = findViewById(R.id.signupbtnsp)

        CoroutineScope(Dispatchers.IO).launch {

        }
    }

    fun login(v:View){
        if(logEmail.text.toString() == "admin" && pwd.text.toString() == "admin"){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    fun signup(v: View){
        val intent = Intent(this, registration::class.java)
        startActivity(intent)
        finish()
    }
}