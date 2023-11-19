package com.example.puhsepuh.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puhsepuh.databinding.ItemObatBinding
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.view.fragment.HomeFragment

typealias OnDeleteButtonClickListener = (obatData: ObatData) -> Unit
typealias OnEditButtonClickListener = (obatData: ObatData) -> Unit
class ObatAdapter(private val obatList: List<ObatData>,
                  private val OnEditButtonClickListener: OnEditButtonClickListener,
                  private val OnDeleteButtonClickListener: OnDeleteButtonClickListener)
    : RecyclerView.Adapter<ObatAdapter.ObatViewHolder>() {

        inner class ObatViewHolder(private val binding: ItemObatBinding)
            : RecyclerView.ViewHolder(binding.root) {
                fun bind(obatData: ObatData) {
                    with(binding) {
                        Log.d("ObatAdapter", obatData.toString())
                        resep.text = obatData.namaObat
                        dosis.text = obatData.dosisMakan.toString() + "x sehari"

                        editButton.setOnClickListener {
                            OnEditButtonClickListener(obatData)
                        }
                        deleteButton.setOnClickListener {
                            OnDeleteButtonClickListener(obatData)
                        }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        val binding = ItemObatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ObatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return obatList.size
    }

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        holder.bind(obatList[position])
    }
}