package com.example.fulltankyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fulltankyou.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val newLoadFragment = NewLoadFragment()
        val fuelStoryFragment = FuelStoryFragment()
        val fuelStatsFragment = FuelStatsFragment()
        val loadHintsFragment = LoadHintsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fuelStoryFragment)
            commit()
        }

        binding.newLoadBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, newLoadFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.fuelStoryBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fuelStoryFragment)
                addToBackStack(null)
                commit()
            }
        }


        binding.fuelStatsBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fuelStatsFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.fuelHintsBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, loadHintsFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}