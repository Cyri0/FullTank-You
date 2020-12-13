package com.example.fulltankyou.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "loadInfo")
data class FuelEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "carKm") val carKm: Double,
    @ColumnInfo(name = "fuel") val fuel: Double,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "gasstation") val gasstation: String
)