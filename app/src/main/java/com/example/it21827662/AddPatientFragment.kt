package com.example.it21827662

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.it21827662.adapters.PatientAdapter
import com.example.it21827662.database.MainActivityData
import com.example.it21827662.database.PatientRepository
import com.example.it21827662.database.Patient
import com.example.it21827662.database.PatientDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.jar.Attributes.Name

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddPatientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPatientFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: PatientAdapter
    private lateinit var viewModel: MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_patient, container, false)
        val repository = PatientRepository(PatientDatabase.getInstance(requireContext()))

        // Use viewLifecycleOwner instead of this
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(MainActivityData::class.java)

        // Find the button in the inflated view
        val btnAddPatient: Button = view.findViewById(R.id.addnew)

        // Add a click listener to the button
        btnAddPatient.setOnClickListener{
            displayAlert(repository)

        }

        return view
    }
    private fun displayAlert(repository: PatientRepository) {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Enter Patient Details")

        val view = layoutInflater.inflate(R.layout.alertviewbox, null)
        builder.setView(view)

        val name: EditText = view.findViewById(R.id.patientName)
        val age: EditText = view.findViewById(R.id.patientAge)
        val patientgender: Spinner = view.findViewById(R.id.gender)
        val patientdoctor: Spinner = view.findViewById(R.id.doctor)

        builder.setPositiveButton("OK") { _, _ ->
            val pName = name.text.toString()
            val pAge = age.text.toString()
            val pGender = patientgender.selectedItem.toString()
            val pDoctor = patientdoctor.selectedItem.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Patient(pName, pAge, pGender, pDoctor))
                val data = repository.getAllPatient()
                withContext(Dispatchers.Main) {
                    viewModel.setData(data)
                    showToast("Patient added successfully!")
                }
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun showToast(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPatientFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddPatientFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}