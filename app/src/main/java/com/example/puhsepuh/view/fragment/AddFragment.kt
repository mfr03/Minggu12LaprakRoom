package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.example.puhsepuh.App
import com.example.puhsepuh.R

import com.example.puhsepuh.databinding.FragmentAddBinding
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.viewmodel.factory.CustomViewModelFactory
import com.example.puhsepuh.viewmodel.viewmodel.AddViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }
    private lateinit var binding : FragmentAddBinding
    private lateinit var viewModel: AddViewModel
    private var listID: MutableList<Int> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = CustomViewModelFactory(requireActivity().application as App)
        viewModel = ViewModelProvider(this, factory).get(AddViewModel::class.java)

        with(binding) {
            addSubmit.setOnClickListener {
                viewModel.addObat(makeObat())
                findNavController().navigateUp()
            }

            addAddInput.setOnClickListener {
                binding.linearLayout.addView(addTimeElements())
            }
        }
    }

    private fun makeObat(): ObatData {
        val tempList: MutableList<String> = mutableListOf()
        for(item in listID) {
            val textInputEditText = binding.root.findViewById<TextInputEditText>(item)
            val text = textInputEditText?.text.toString()
            tempList.add(text)
        }
        with(binding) {
            val obat = ObatData(
                namaObat = inputAddNamaObat.text.toString(),
                gejalaPenyakit = inputAddGejalaPenyakit.text.toString(),
                dosisMakan = inputAddJumlabObatWaktu.text.toString().toInt(),
                dosisHari = inputAddJumlahObatSehari.text.toString().toInt(),
                dosisLamaPengobatan = inputAddLamaPengobatan.text.toString().toInt(),
                isSetelahMakan = if(addSetelahMakan.isChecked) true else if (addSebelumMakan.isChecked) false else null,
                waktuNotifikasi = tempList
            )
            return obat
        }
    }


    private fun addTimeElements(): TextInputLayout {

        val textInputLayout = TextInputLayout(requireContext(), null, com.google.android.material.R.style.Widget_Material3_TextInputLayout_OutlinedBox).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            hint = "Masukkan Waktu"

            val textInputEditText = TextInputEditText(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                id = View.generateViewId()
                listID.add(id)
            }
            addView(textInputEditText)
        }
        return textInputLayout
    }
}