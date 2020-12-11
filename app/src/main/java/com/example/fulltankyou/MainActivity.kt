package com.example.fulltankyou

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var actualKm: EditText
    lateinit var actualL: EditText
    lateinit var actualPrice: EditText
    lateinit var gasStation: Spinner
    lateinit var actualDate: TextView
    lateinit var currentDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actualDate = findViewById(R.id.actualdate)
        actualKm = findViewById(R.id.actualkm)
        actualL = findViewById(R.id.actualpetrol)
        actualPrice = findViewById(R.id.actualmoney)

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
        currentDate = simpleDateFormat.format(Date())

        actualDate.text = currentDate


        gasStation = findViewById(R.id.actualgasstation)
        ArrayAdapter.createFromResource(
            this,
            R.array.gas_stations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gasStation.adapter = adapter
        }


        val saveRefuelingBtn: Button = findViewById(R.id.save_refuelingBtn);
        saveRefuelingBtn.setOnClickListener {
            saveRefueling()
        }
    }

    private fun saveRefueling() {
        var actualKmNum = 0.0
        var actualPriceNum = 0.0
        var actualLNum = 0.0

        if(!actualKm.text.isNullOrEmpty())
            actualKmNum = actualKm.text.toString().toDouble()

        if(!actualL.text.isNullOrEmpty())
            actualLNum = actualL.text.toString().toDouble()

        if(!actualPrice.text.isNullOrEmpty())
            actualPriceNum = actualPrice.text.toString().toDouble()

        val gasstation = gasStation.getSelectedItem().toString();

        var errorMsg = ""

        if(actualKmNum <= 0){
            errorMsg += "Hibás a km megadása! "
        }
        if(actualLNum <= 0){
            errorMsg += "Hibás a mennyiség megadása! "
        }
        if(actualPriceNum <= 0){
            errorMsg += "Hibás az ár megadása! "
        }

        if(errorMsg.isEmpty()) {
            Toast.makeText(this, "Km: " + actualKmNum + " L: " + actualLNum + " Ár: " + actualPriceNum + "Ft" + " Benzinkút: " + gasstation, Toast.LENGTH_SHORT).show()

            val actual = Refuel(actualLNum, actualKmNum, actualPriceNum, gasstation, Date())

            println()
            println(actual.toString())
            println()
            println(actual.getDateString())

        } else{
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}

class Refuel {

    private var fuelLiter: Double
    private var actualKm: Double
    private var price: Double
    private var stationName: String
    private var date: Date

    constructor(fuelLiter: Double, actualKm: Double, price: Double, stationName: String, date: Date){
        this.fuelLiter = fuelLiter
        this.actualKm = actualKm
        this.price = price
        this.stationName = stationName
        this.date = date
    }

    fun getDateString(): String{
        return SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(this.date)
    }

    override fun toString(): String {
        return "Refuel(fuelLiter=$fuelLiter, actualKm=$actualKm, price=$price, stationName='$stationName')"
    }
}