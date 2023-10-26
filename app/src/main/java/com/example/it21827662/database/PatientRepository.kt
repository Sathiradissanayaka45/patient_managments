package com.example.it21827662.database

class PatientRepository(
    private val db: PatientDatabase
){
    suspend fun insert(patient: Patient) = db.getPatientDao().insert(patient)
    suspend fun delete(patient: Patient) = db.getPatientDao().delete(patient)
    //fun getAllPateints():List<Patient> = db.getPatientDao().getAllPatient()

    fun getAllPatient():List<Patient> = db.getPatientDao().getAllPatient()
}