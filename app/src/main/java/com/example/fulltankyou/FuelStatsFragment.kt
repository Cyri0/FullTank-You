package com.example.fulltankyou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.fulltankyou.databinding.FragmentFuelStatsBinding

class FuelStatsFragment : Fragment() {

    private lateinit var binding: FragmentFuelStatsBinding
    lateinit var lperhundredkm: TextView
    lateinit var usedFuel: TextView
    lateinit var price: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,
            R.layout.fragment_fuel_stats, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        lperhundredkm = binding.lPerHKm
        usedFuel = binding.usedFuel
        price = binding.price
        try{

            lperhundredkm.text = "" + "%.2f".format(lastFuelLoad.last.getPetrolPerHundredKm(lastFuelLoad.penult)) + " l / 100km"
            usedFuel.text = "" + "%.2f".format(lastFuelLoad.last.getFuelLiter()) + " l"
            price.text = "" + "%.0f".format(lastFuelLoad.last.getPrice()) + " Ft"

        }catch(e: Exception){

        }
    }

}