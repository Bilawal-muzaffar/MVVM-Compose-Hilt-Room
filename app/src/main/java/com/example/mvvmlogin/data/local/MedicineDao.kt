package com.example.mvvmlogin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmlogin.data.model.Drug

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<Drug>)

    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicines(): List<Drug>

    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    suspend fun getMedicineDetail(medicineId: String): Drug
}
