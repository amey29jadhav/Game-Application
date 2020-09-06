package com.amey.gameapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.amey.gameapplication.R
import com.amey.gameapplication.model.ArmorModel

class GameAdapter(var list: List<ArmorModel>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    class GameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.game_row, parent, false)) {
        var name: AppCompatTextView

        init {
            name = itemView.findViewById(R.id.name)
        }

        fun bind(armorModel: ArmorModel) {
            name.text = armorModel.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(LayoutInflater.from(parent.context), parent)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setValue(updateList: List<ArmorModel>) {
        list = updateList
    }
}