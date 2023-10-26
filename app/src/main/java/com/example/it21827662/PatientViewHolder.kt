package com.example.it21827662

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PatientViewHolder(view: View) : ViewHolder(view) {
    val pbtn:CheckBox = view.findViewById(R.id.checkBox)
    val pName: TextView = view.findViewById(R.id.ptName)
    val pDoctor: TextView = view.findViewById(R.id.pdoctor)
    val pDelete: ImageView= view.findViewById(R.id.delete)
}