package com.louati.weather.modules.towns.userInterface.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.louati.weather.databinding.ItemTownBinding
import com.louati.weather.modules.towns.userInterface.models.TownsViewItemInterface

@SuppressLint("NotifyDataSetChanged")
class TownsRecyclerViewAdapter(
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<TownsRecyclerViewAdapter.TownsHolder>() {

    private lateinit var context: Context
    private var townsList: ArrayList<TownsViewItemInterface>

    init {
        this.townsList = ArrayList()
    }

    fun setItems(list: ArrayList<TownsViewItemInterface>) {
        this.townsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TownsHolder {
        val binding = ItemTownBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return TownsHolder(binding)
    }

    override fun onBindViewHolder(holder: TownsHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = townsList.size

    inner class TownsHolder(private val binding: ItemTownBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val town = townsList[position]

            binding.apply {
                tvName.text = town.name
                this.root.setOnClickListener { onItemSelected(position) }
            }
        }
    }
}