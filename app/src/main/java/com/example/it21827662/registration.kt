package com.example.it21827662

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.it21827662.database.UserRepository
import com.example.it21827662.database.User_database
import com.example.it21827662.database.Users
import com.example.it21827662.validation.RegisFormData
import com.example.it21827662.validation.regisValidation.RegisValidationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class registration : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var genders : Spinner
    lateinit var email: EditText
    lateinit var pwd : EditText
    lateinit var signup : Button
    lateinit var backtolog : Button
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userRepo = UserRepository(User_database.getInstance(this))

        username = findViewById(R.id.username)
        genders = findViewById(R.id.gender)
        email = findViewById(R.id.userEmail)
        pwd = findViewById(R.id.signpwd)
        signup = findViewById(R.id.signupbtnsp)
        backtolog = findViewById(R.id.backtologbtn)

        backtolog.setOnClickListener(){
            val intent = Intent(this, activityMain2::class.java)
            startActivity(intent)
            finish()
        }

        signup.setOnClickListener(){
            val userName = username.text.toString()
            val userGender = genders.selectedItem.toString()
            val userEmail = email.text.toString()
            val userPWD = pwd.text.toString()
            val regisForamData = RegisFormData(
                userName,
                userGender,
                userEmail,
                userPWD
            )

            val userNameValidation = regisForamData.validateUsername()
            val genderValidation = regisForamData.validateGender()
            val emailValidation = regisForamData.validateEmail()
            val passwordValidation = regisForamData.validatePassword()

            when(userNameValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    username.error = userNameValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    username.error = userNameValidation.errorMessage
                }
            }

            when(genderValidation){
                is RegisValidationResult.Valid ->{
                    count ++
                }
                is RegisValidationResult.Invalid ->{
                    val tv: TextView = genders.selectedView as TextView
                    tv.error =""
                    tv.text = genderValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    val tv: TextView = genders.selectedView as TextView
                    tv.error =""
                    tv.text = genderValidation.errorMessage
                }
            }

            when(emailValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    email.error = emailValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    email.error = emailValidation.errorMessage
                }
            }

            when(passwordValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    pwd.error = passwordValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    pwd.error = passwordValidation.errorMessage
                }
            }

            if(count==4){
                Toast.makeText(this,"Registration succesful", Toast.LENGTH_LONG).show()
                CoroutineScope(Dispatchers.IO).launch {
                    userRepo.insert(Users(userName,userGender,userEmail,userPWD))
                }
                val intent = Intent(this, activityMain2::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}