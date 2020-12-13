package com.example.fulltankyou

import java.text.SimpleDateFormat
import java.util.*

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