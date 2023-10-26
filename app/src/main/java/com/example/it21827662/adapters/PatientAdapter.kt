package com.example.it21827662.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.it21827662.PatientViewHolder
import com.example.it21827662.R  // Make sure you import your R class
import com.example.it21827662.database.MainActivityData
import com.example.it21827662.database.PatientRepository
import com.example.it21827662.database.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientAdapter (
    private val items: List<Patient>,
    private val repository: PatientRepository,
    private val viewModel: MainActivityData
) : RecyclerView.Adapter<PatientViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        context = parent.context

        return PatientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.pbtn.text = items[position].name
        holder.pName.text = items[position].age
        holder.pDoctor.text = items[position].doctor
        holder.pDelete.setOnClickListener {
            val isChecked = holder.pbtn.isChecked

            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items[position])
                    val data = repository.getAllPatient()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
            } else
                Toast.makeText(context, "Select patient to delete", Toast.LENGTH_LONG).show()
        }
    }
}