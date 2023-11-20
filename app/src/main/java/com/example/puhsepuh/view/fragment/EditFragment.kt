package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.puhsepuh.App
import com.example.puhsepuh.R
import com.example.puhsepuh.databinding.FragmentEditBinding
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.viewmodel.factory.CustomViewModelFactory
import com.example.puhsepuh.viewmodel.viewmodel.EditViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }
    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: EditViewModel
    private var listID: MutableList<Int> = mutableListOf()
    private var currentID: Int = 0
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
            currentID = obatId
            val obatData: LiveData<ObatData> = viewModel.getObat(obatId)
            obatData.observe(viewLifecycleOwner) {
                setValues(it)
            }
        }

        with(binding) {
            editSubmit.setOnClickListener {
                viewModel.updateObat(makeObat())
                findNavController().navigateUp()
            }
        }

    }

    private fun setValues(obatData: ObatData) {
        for(item in obatData.waktuNotifikasi) {
            binding.linearLayout.addView(addTimeElements(item))
        }
        with(binding) {
            inputEditGejalaPenyakit.setText(obatData.gejalaPenyakit)
            inputEditNamaObat.setText(obatData.namaObat)
            inputEditJumlabObatWaktu.setText(obatData.dosisMakan.toString())
            inputEditJumlahObatSehari.setText(obatData.dosisHari.toString())
            inputEditLamaPengobatan.setText(obatData.dosisLamaPengobatan.toString())
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
                id = currentID,
                namaObat = inputEditNamaObat.text.toString(),
                gejalaPenyakit = inputEditGejalaPenyakit.text.toString(),
                dosisMakan = inputEditJumlabObatWaktu.text.toString().toInt(),
                dosisHari = inputEditJumlahObatSehari.text.toString().toInt(),
                dosisLamaPengobatan = inputEditLamaPengobatan.text.toString().toInt(),
                isSetelahMakan = if(editSetelahMakan.isChecked) true else if (editSebelumMakan.isChecked) false else null,
                waktuNotifikasi = tempList
            )
            return obat
        }
    }

    private fun addTimeElements(string: String): TextInputLayout {

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
                setText(string)
            }
            addView(textInputEditText)
        }
        return textInputLayout
    }

}