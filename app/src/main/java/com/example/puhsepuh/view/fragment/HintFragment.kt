package com.example.puhsepuh.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.puhsepuh.viewmodel.viewmodel.HintViewModel
import com.example.puhsepuh.R

class HintFragment : Fragment() {

    companion object {
        fun newInstance() = HintFragment()
    }

    private lateinit var viewModel: HintViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hint, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HintViewModel::class.java)
        // TODO: Use the ViewModel
    }

}