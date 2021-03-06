package com.example.fulltankyou

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fulltankyou.db.FuelEntity
import kotlinx.android.synthetic.main.story_card_layout.view.*

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<FuelEntity>()

    fun setListData(data: ArrayList<FuelEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.story_card_layout, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getMyItems(): ArrayList<FuelEntity>{
        return this.items
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val tvDate = view.tvDate
        private val tvCarKm = view.tvCarKm
        private val tvFuel = view.tvFuel
        private val tvPrice = view.tvPrice

        fun bind(data: FuelEntity){
            tvDate.text = data.date + " - " + data.gasstation
            tvCarKm.text = "Km óra állása: " + data.carKm.toString()
            tvFuel.text = "Tankolt üzemanyag: " + data.fuel.toString() + " l"
            tvPrice.text = "Fizetett összeg: " + data.price.toString() + " Ft"
        }


    }
}