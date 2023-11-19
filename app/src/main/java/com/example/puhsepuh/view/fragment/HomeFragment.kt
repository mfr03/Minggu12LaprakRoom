package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puhsepuh.App
import com.example.puhsepuh.viewmodel.viewmodel.HomeViewModel
import com.example.puhsepuh.databinding.FragmentHomeBinding
import com.example.puhsepuh.recyclerview.ObatAdapter
import com.example.puhsepuh.viewmodel.factory.CustomViewModelFactory

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()

    }


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = CustomViewModelFactory(requireActivity().application as App)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        with(binding) {

            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            addButton.setOnClickListener {
                findNavController().navigate(action)
            }

            viewModel.getAllData().observeForever() { obat ->
                val adapter = ObatAdapter(obat,
                    {obatData ->
                        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment()
                        findNavController().apply{
                            currentBackStackEntry?.
                            savedStateHandle?.set("obatId", obatData.id)
                        }.navigate(action)
                    },

                    {obatData ->
                        viewModel.deleteObat(obatData)
                    }
                )


                obatRecyclerView.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }

    }


}