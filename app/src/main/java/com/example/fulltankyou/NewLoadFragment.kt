package com.example.fulltankyou


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fulltankyou.databinding.FragmentNewLoadBinding
import com.example.fulltankyou.db.FuelEntity
import java.text.SimpleDateFormat
import java.util.*

class NewLoadFragment : Fragment(){



    private lateinit var binding: FragmentNewLoadBinding
    lateinit var actualKm: EditText
    lateinit var actualL: EditText
    lateinit var actualPrice: EditText
    lateinit var gasStation: Spinner
    lateinit var actualDate: TextView
    lateinit var currentDate: String
    lateinit var viewModel: StoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,
        R.layout.fragment_new_load, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        actualDate = binding.actualdate
        actualKm = binding.actualkm
        actualL = binding.actualpetrol
        actualPrice = binding.actualmoney

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
        currentDate = simpleDateFormat.format(Date())

        actualDate.text = currentDate

        gasStation = binding.actualgasstation

        gasStation?.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                super.getResources().getStringArray(R.array.gas_stations)
            )
        } as SpinnerAdapter

        gasStation?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
            }
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

        val gasStation = gasStation.getSelectedItem().toString();

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

            val actual = Refuel(actualLNum, actualKmNum, actualPriceNum, gasStation, currentDate)

            calculateConsumption(lastFuelLoad.last, actual)
            println(actual)

            val id = SimpleDateFormat("yyMMddHHmmss").format(Date()).toString()
            println(lastFuelLoad.last)

            viewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)

            viewModel.insertLoadInfo(FuelEntity(id.toLong(), actual.getDateString(), actual.getActualKm(), actual.getFuelLiter(), actual.getPrice(), actual.getStationName()))

            try{
                lastFuelLoad.penult = lastFuelLoad.last
                lastFuelLoad.last = actual
            }
            catch (e: Exception) {
                print("\nProbléma a penult <-- last áttöltésnél\n")
            }

            actualKm.text = null
            actualL.text = null
            actualPrice.text = null

        } else{
           Toast.makeText(super.getContext() , errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateConsumption(last: Refuel, new:Refuel){

        Toast.makeText(super.getContext(),
            "Megtett táv: " + new.getDistanceTraveled(last) +
                  " Fogyasztás 100 km-en: " + "%.2f".format(new.getPetrolPerHundredKm(last)) + "l" +
                  " egy liter ára: " + "%.1f".format(new.getPricePerLiter()),
            Toast.LENGTH_SHORT).show()
    }
}