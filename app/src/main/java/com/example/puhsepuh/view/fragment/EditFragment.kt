package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.puhsepuh.App
import com.example.puhsepuh.R
import com.example.puhsepuh.databinding.FragmentEditBinding
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.viewmodel.factory.CustomViewModelFactory
import com.example.puhsepuh.viewmodel.viewmodel.EditViewModel

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }
    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: EditViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = CustomViewModelFactory(requireActivity().application as App)
        viewModel = ViewModelProvider(this, factory).get(EditViewModel::class.java)

        findNavController().previousBackStackEntry?.savedStateHandle?.getLiveData<Int>("obatId")?.observe(viewLifecycleOwner) { obatId ->
            val obatData: LiveData<ObatData> = viewModel.getObat(obatId)
            obatData.observe(viewLifecycleOwner) {
                setValues(it)
            }
        }

    }

    private fun setValues(obatData: ObatData) {
        with(binding) {
            inputEditGejalaPenyakit.setText(obatData.gejalaPenyakit)
            inputEditNamaObat.setText(obatData.namaObat)
            inputEditJumlabObatWaktu.setText(obatData.dosisMakan.toString())
            inputEditJumlahObatSehari.setText(obatData.dosisHari.toString())
            inputEditLamaPengobatan.setText(obatData.dosisLamaPengobatan.toString())
        }
    }

}