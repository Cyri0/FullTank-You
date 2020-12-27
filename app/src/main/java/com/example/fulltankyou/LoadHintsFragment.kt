package com.example.fulltankyou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.fulltankyou.databinding.FragmentLoadHintsBinding
import com.mashape.unirest.http.Unirest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.supportFragmentUiThread
import org.json.JSONObject

class LoadHintsFragment : Fragment() {

    private lateinit var binding: FragmentLoadHintsBinding
    lateinit var hintText: TextView

    var euro: Double = 360.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,
            R.layout.fragment_load_hints, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        hintText = binding.hintText


        doAsync {
           var response =
               Unirest.get("https://gas-price.p.rapidapi.com/europeanCountries")
                   .header("x-rapidapi-key", "b0c55cd571mshdbc14498ae98b45p10f2bejsn052c0a0d6324")
                   .header("x-rapidapi-host", "gas-price.p.rapidapi.com")
                   .asString()


            hintText.text = "Teszt..."
            supportFragmentUiThread {


                try {
                    var hun = JSONObject(response.body).getJSONArray("results")[18]

                    var hunData = JSONObject(hun.toString())
                    var lpg = hunData.getDouble("lpg") * euro
                    var diesel = hunData.getDouble("diesel") * euro
                    var gasoline = hunData.getDouble("gasoline") * euro

                    hintText.text = "Autógáz ára: " + lpg + " Ft\n\n" +
                            "Dízel üzemanyag ára: " + diesel + " Ft\n\n" +
                            "Benzin ára: " + gasoline + " Ft\n\n"
                }
                catch(e: Exception){
                    hintText.text = "Probléma az adatbázis elérésével:\n\n" + JSONObject(response.body).getString("message")
                }


            }
        }

    }
}