package com.example.fulltankyou

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewLoadFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_load, container, false)
    }
}




/*

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
    } */



