package com.example.it21827662

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val patientlistFragment = PatientlistFragment()
    private val addPatientFragment = AddPatientFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.addPatient)
        val button2: Button = findViewById(R.id.patientList)

        button.setOnClickListener {
            loadAddPatient()
        }

        button2.setOnClickListener {
            loadPatientList()
        }
    }

    private fun loadAddPatient() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, addPatientFragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, addPatientFragment).commit()
        }
    }

    private fun loadPatientList() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, patientlistFragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, patientlistFragment).commit()
        }
    }
}