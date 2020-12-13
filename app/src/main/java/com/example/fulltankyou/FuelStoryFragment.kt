package com.example.fulltankyou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fulltankyou.databinding.FragmentFuelStoryBinding
import com.example.fulltankyou.db.FuelEntity

class FuelStoryFragment : Fragment(), RecyclerViewAdapter.RowClickListener {

    private lateinit var binding: FragmentFuelStoryBinding

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,
            R.layout.fragment_fuel_story, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(super.getActivity())
            recyclerViewAdapter = RecyclerViewAdapter(this@FuelStoryFragment, binding)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(super.getActivity(), VERTICAL)
            addItemDecoration(divider)
        }
    }

    override fun onDeleteFuelLoadClickListener(load: FuelEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(load: FuelEntity) {
        TODO("Not yet implemented")
    }

}