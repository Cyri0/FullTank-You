package com.example.fulltankyou

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.fulltankyou.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var actualKm: EditText
    lateinit var actualL: EditText
    lateinit var actualPrice: EditText
    lateinit var gasStation: Spinner
    lateinit var actualDate: TextView
    lateinit var currentDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        actualDate = binding.actualdate
        actualKm = binding.actualkm
        actualL = binding.actualpetrol
        actualPrice = binding.actualmoney

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
        currentDate = simpleDateFormat.format(Date())

        actualDate.text = currentDate

        gasStation = binding.actualgasstation
        ArrayAdapter.createFromResource(
            this,
            R.array.gas_stations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gasStation.adapter = adapter
        }

        val saveRefuelingBtn: Button = binding.saveRefuelingBtn;
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
            val actual = Refuel(actualLNum, actualKmNum, actualPriceNum, gasstation, Date())
            calculateConsumption(Refuel(25.0,150000.0, 87000.0, "-", Date()), actual)
            println(actual.getDateString())

        } else{
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateConsumption(last: Refuel, new:Refuel){
        var distanceTraveled = new.getActualKm() - last.getActualKm()
        var petrolPerHundredKm =  (new.getFuelLiter() / distanceTraveled) * 100
        var pricePerLiter = new.getPrice() / new.getFuelLiter()

        Toast.makeText(this,
                    "Megtett táv: " + distanceTraveled +
                        " Fogyasztás 100 km-en: " + "%.2f".format(petrolPerHundredKm) + "l" +
                        " egy liter ára: " + "%.1f".format(pricePerLiter),
                Toast.LENGTH_SHORT).show()
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

    fun getFuelLiter():Double{
        return fuelLiter
    }

    fun getActualKm():Double{
        return actualKm
    }

    fun getPrice():Double{
        return price
    }

    fun getStationName():String{
        if(stationName == "-" || stationName == "Egyéb")
            return ""
        return stationName
    }

    override fun toString(): String {
        return "Refuel(fuelLiter=$fuelLiter, actualKm=$actualKm, price=$price, stationName='$stationName')"
    }
}