package com.example.fulltankyou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
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
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val divider = DividerItemDecoration(activity, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)

        viewModel.getAllFuelLoadsObservers().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

//        viewModel.insertLoadInfo(FuelEntity(1002, "2020.01.12", 10000.0, 20.0, 10000.0))
//        viewModel.insertLoadInfo(FuelEntity(1003, "2020.02.12", 11000.0, 28.0, 18000.0))


       // val fuelLoad = FuelEntity(0, "2021.08.07.",102340.0,22.0,8050.0)
       // viewModel.insertLoadInfo(fuelLoad)

    }
}