package com.example.it21827662.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class User_database : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object{
        @Volatile
        private var INSTANCE : User_database? = null

        fun getInstance(context: Context): User_database {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    User_database::class.java, "User_Databse"
                ).build().also {
                    INSTANCE=it
                }
            }
        }
    }
}