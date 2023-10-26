package com.example.it21827662.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    var Uname : String?,
    var UserGender : String?,
    var userEmail : String?,
    var userPwd : String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
