package com.example.fulltankyou

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fulltankyou.db.FuelEntity
import com.example.fulltankyou.db.RoomAppDb

class StoryViewModel(app: Application): AndroidViewModel(app) {

    lateinit var allLoads: MutableLiveData<List<FuelEntity>>

    init{
        allLoads = MutableLiveData()
    }

    fun getAllFuelLoadsObservers(): MutableLiveData<List<FuelEntity>>{
        return allLoads
    }

    fun getAllFuelLoads(){
        val fuelDao = RoomAppDb.getAppDatabase((getApplication()))?.fuelDao()
        val list = fuelDao?.getAllFuelLoadInfo()

        allLoads.postValue(list)
    }

    fun insertLoadInfo(entity: FuelEntity){
        var fuelDao = RoomAppDb.getAppDatabase(getApplication())?.fuelDao()
        fuelDao?.insertFuelLoad(entity)
        getAllFuelLoads()
    }

    fun updateLoadInfo(entity: FuelEntity){
        var fuelDao = RoomAppDb.getAppDatabase(getApplication())?.fuelDao()
        fuelDao?.updateFuelLoad(entity)
        getAllFuelLoads()
    }

    fun deleteLoadInfo(entity: FuelEntity){
        var fuelDao = RoomAppDb.getAppDatabase(getApplication())?.fuelDao()
        fuelDao?.deleteFuelLoad(entity)
        getAllFuelLoads()
    }

}