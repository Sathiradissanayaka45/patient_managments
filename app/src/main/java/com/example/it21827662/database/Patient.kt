package com.example.it21827662.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient (
    var name: String?,
    var age : String?,
    var gender: String?,
    var doctor : String?,

    ){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
