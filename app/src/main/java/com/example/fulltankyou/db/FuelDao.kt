package com.example.fulltankyou.db

import androidx.room.*

@Dao
interface FuelDao {

    @Query("SELECT * FROM loadInfo ORDER BY id DESC")
    fun getAllFuelLoadInfo(): List<FuelEntity>?

    @Insert
    fun insertFuelLoad(load: FuelEntity?)

    @Delete
    fun deleteFuelLoad(load: FuelEntity?)

    @Update
    fun updateFuelLoad(load: FuelEntity?)
}