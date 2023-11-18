package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puhsepuh.viewmodel.HomeViewModel
import com.example.puhsepuh.R
import com.example.puhsepuh.databinding.FragmentHomeBinding
import com.example.puhsepuh.recyclerview.ObatAdapter

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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.fetchData()
        val data = viewModel.data.value
        with(binding) {

            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            Log.d("HomeFragment", "$action")
            addButton.setOnClickListener {
                findNavController().navigate(action)
                Log.d("HomeFragment", "Add button clicked")
            }

            Log.d("HomeFragment", data.toString())
            val adapter = ObatAdapter(listOf(data!!))
            obatRecyclerView.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

}