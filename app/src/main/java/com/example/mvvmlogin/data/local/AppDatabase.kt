package com.example.mvvmlogin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmlogin.data.model.Drug


@Database(entities = [Drug::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}
