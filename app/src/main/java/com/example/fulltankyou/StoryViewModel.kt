package com.example.fulltankyou

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.fulltankyou.db.FuelEntity
import com.example.fulltankyou.db.RoomAppDb

class StoryViewModel(app: Application): AndroidViewModel(app) {

    var allLoads: MutableLiveData<List<FuelEntity>> = MutableLiveData()

    init{
        allLoads = MutableLiveData()
        Transformations.map(allLoads){
            it
            // itt van a LiveData transzformáció önmagára TODO
        }
        getAllFuelLoads()
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

    fun deleteLoadInfo(entity: FuelEntity){
        var fuelDao = RoomAppDb.getAppDatabase(getApplication())?.fuelDao()
        fuelDao?.deleteFuelLoad(entity)
        getAllFuelLoads()
    }

}