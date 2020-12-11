package com.example.fulltankyou

import android.icu.util.TimeUnit
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.*
import org.jetbrains.annotations.Nullable

class MainActivity : AppCompatActivity() {

    lateinit var actualKm: EditText
    lateinit var actualL: EditText
    lateinit var actualPrice: EditText

    lateinit var gasStation: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actualKm = findViewById(R.id.actualkm)
        actualL = findViewById(R.id.actualpetrol)
        actualPrice = findViewById(R.id.actualmoney)
        
        gasStation = findViewById(R.id.actualgasstation)
        ArrayAdapter.createFromResource(
            this,
            R.array.gas_stations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gasStation.adapter = adapter
        }


        val save_refuelingBtn: Button = findViewById(R.id.save_refuelingBtn);
        save_refuelingBtn.setOnClickListener {
            save_refueling()
        }
    }

    private fun save_refueling() {

        var actualKmNum = 0.0
        var actualPriceNum = 0.0
        var actualLNum = 0.0

        if(!actualKm.text.isNullOrEmpty())
            actualKmNum = actualKm.text.toString().toDouble()

        if(!actualL.text.isNullOrEmpty())
            actualLNum = actualL.text.toString().toDouble()

        if(!actualPrice.text.isNullOrEmpty())
            actualPriceNum = actualPrice.text.toString().toDouble()

       // val gasstation = gasStation.onItemSelectedListener

        var error_msg = ""

        if(actualKmNum <= 0){
            error_msg += "Hibás a km megadása! "
        }
        if(actualLNum <= 0){
            error_msg += "Hibás a mennyiség megadása! "
        }
        if(actualPriceNum <= 0){
            error_msg += "Hibás az ár megadása! "
        }

        if(error_msg.isEmpty()) {
            Toast.makeText(this, "Km: " + actualKmNum + " L: " + actualLNum + " Ár: " + actualPriceNum + "Ft", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, error_msg, Toast.LENGTH_SHORT).show()
        }
    }
}
