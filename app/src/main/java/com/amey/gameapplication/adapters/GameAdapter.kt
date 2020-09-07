package com.amey.gameapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amey.gameapplication.R
import com.amey.gameapplication.model.ArmorModel

class GameAdapter(var list: List<ArmorModel>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    class GameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.game_row, parent, false)) {
        var name: AppCompatTextView? = null
        var rank: AppCompatTextView? = null
        var base: AppCompatTextView? = null
        var slots: AppCompatTextView? = null
        var type: ImageView? = null
        var baseimage: ImageView? = null
        private var mycontext: Context


        init {
            name = itemView.findViewById(R.id.name)
            rank = itemView.findViewById(R.id.rank)
            base = itemView.findViewById(R.id.base)
            slots = itemView.findViewById(R.id.slots)
            type = itemView.findViewById(R.id.type)
            baseimage = itemView.findViewById(R.id.baseimage)
            mycontext = parent.context

        }

        fun bind(armorModel: ArmorModel) {
            name?.text = armorModel.name
            rank?.text = armorModel.rank
            base?.text = armorModel.defense.base.toString()
            baseimage?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_shield))

            slots?.text = armorModel.slots.size.toString()

            if (armorModel.type.toLowerCase().contentEquals(Type.Head.name.toLowerCase())) {
                type?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_head))
            } else if (armorModel.type.toLowerCase().contentEquals(Type.Legs.name.toLowerCase())) {
                type?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_legs))

            } else if (armorModel.type.toLowerCase().contentEquals(Type.Chest.name.toLowerCase())) {
                type?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_chest))

            } else if (armorModel.type.toLowerCase().contentEquals(Type.Waist.name.toLowerCase())) {
                type?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_waist))

            } else if (armorModel.type.toLowerCase()
                    .contentEquals(Type.Gloves.name.toLowerCase())
            ) {
                type?.setImageDrawable(ContextCompat.getDrawable(mycontext, R.drawable.ic_waist))
            }


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

    /***
     * Enum to create Type Constants
     */
    enum class Type {
        Head, Legs, Chest, Waist, Gloves
    }
}