package com.example.fulltankyou.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FuelEntity::class], version = 1)
abstract class RoomAppDb: RoomDatabase() {

    abstract fun fuelDao(): FuelDao?

    companion object{
        private var INSTANCE: RoomAppDb ?= null

        fun getAppDatabase(context: Context): RoomAppDb ? {
            if(INSTANCE == null ){
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext,
                    RoomAppDb::class.java,
                    "TankYouFuelLoadDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}