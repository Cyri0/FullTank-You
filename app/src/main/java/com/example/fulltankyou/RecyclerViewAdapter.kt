package com.example.fulltankyou

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fulltankyou.databinding.FragmentFuelStoryBinding
import com.example.fulltankyou.db.FuelEntity

class RecyclerViewAdapter(private val listener: RowClickListener, private val storyBinding: FragmentFuelStoryBinding): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<FuelEntity>()

    fun setListData(data: ArrayList<FuelEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.fragment_fuel_story, parent, false)
        return MyViewHolder(inflater,listener, storyBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
            }
        holder.bind(items[position])
    }

    class MyViewHolder(view: View, private val listener: RowClickListener, private val storyBinding: FragmentFuelStoryBinding) : RecyclerView.ViewHolder(view){

        private val tvDate = storyBinding.tvDate
        private val tvCarKm = storyBinding.tvCarKm
        private val tvFuel = storyBinding.tvFuel
        private val tvPrice = storyBinding.tvPrice
        private val deleteLoadID = storyBinding.deleteLoadID

        fun bind(data: FuelEntity){
            tvDate.text = data.date
            tvCarKm.text = data.carKm.toString()
            tvFuel.text = data.fuel.toString()
            tvPrice.text = data.price.toString()

            deleteLoadID.setOnClickListener {
                listener.onDeleteFuelLoadClickListener(data)
                }
        }
    }

    interface RowClickListener{
        fun onDeleteFuelLoadClickListener(load: FuelEntity)
        fun onItemClickListener(load: FuelEntity)
    }

}