package com.example.fulltankyou

import java.text.SimpleDateFormat
import java.util.*

class Refuel {

    private var fuelLiter: Double
    private var actualKm: Double
    private var price: Double
    private var stationName: String
    private var date: String

    constructor(fuelLiter: Double, actualKm: Double, price: Double, stationName: String, date: String){
        this.fuelLiter = fuelLiter
        this.actualKm = actualKm
        this.price = price
        this.stationName = stationName
        this.date = date
    }

    fun getDateString(): String{
        return this.date
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


    fun getPricePerLiter(): Double {
        return this.getPrice() / this.getFuelLiter()
    }



    fun getDistanceTraveled(last: Refuel):Double{
        return this.getActualKm() - last.getActualKm();
    }
    fun getPetrolPerHundredKm(last: Refuel):Double{
        return (this.getFuelLiter() / this.getDistanceTraveled(last)) * 100
    }


    override fun toString(): String {
        return "Refuel(fuelLiter=$fuelLiter, actualKm=$actualKm, price=$price, stationName='$stationName')"
    }

}