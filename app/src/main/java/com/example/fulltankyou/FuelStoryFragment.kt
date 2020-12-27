package com.example.fulltankyou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_fuel_story.*


class FuelStoryFragment : Fragment() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    lateinit var viewModel: StoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fuel_story, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        refresh()
    }

    fun refresh(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }

        val divider = DividerItemDecoration(activity, LinearLayoutManager.HORIZONTAL)
        recyclerView.addItemDecoration(divider)

        viewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        viewModel.getAllFuelLoadsObservers().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()


            try {
                var lastLoadData = recyclerViewAdapter.getMyItems()[0]
                val last = Refuel(lastLoadData.fuel, lastLoadData.carKm, lastLoadData.price, lastLoadData.gasstation, lastLoadData.date)
                lastFuelLoad.last = last
                var penultLoadData = recyclerViewAdapter.getMyItems()[1]
                val penult = Refuel(penultLoadData.fuel, penultLoadData.carKm, penultLoadData.price, penultLoadData.gasstation, penultLoadData.date)
                lastFuelLoad.penult = penult
            }
            catch (e: Exception) {
                // handler TODO
                print("\nHiba az adatbázis kiolvasásánál!\n")
            }

        })
    }
}