package com.example.it21827662.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PatientDao {
    @Insert
    suspend fun insert(patient: Patient)

    @Delete
    suspend fun delete(patient: Patient)

    @Query("SELECT * FROM Patient")
    fun getAllPatient():List<Patient>

//    @Query("SELECT * FROM Patient WHERE id=:id")
//    fun getOne(id:Int):Patient
}