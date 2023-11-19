package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.puhsepuh.App

import com.example.puhsepuh.databinding.FragmentAddBinding
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.viewmodel.factory.CustomViewModelFactory
import com.example.puhsepuh.viewmodel.viewmodel.AddViewModel

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }
    private lateinit var binding : FragmentAddBinding
    private lateinit var viewModel: AddViewModel

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
        }
    }

    private fun makeObat(): ObatData {
        with(binding) {
            val obat = ObatData(
                namaObat = inputAddNamaObat.text.toString(),
                gejalaPenyakit = inputAddGejalaPenyakit.text.toString(),
                dosisMakan = inputAddJumlabObatWaktu.text.toString().toInt(),
                dosisHari = inputAddJumlahObatSehari.text.toString().toInt(),
                dosisLamaPengobatan = inputAddLamaPengobatan.text.toString().toInt(),
                isSetelahMakan = if(addSetelahMakan.isChecked) true else if (addSebelumMakan.isChecked) false else null,
                waktuNotifikasi = "Siang"
            )
            return obat
        }

    }
}