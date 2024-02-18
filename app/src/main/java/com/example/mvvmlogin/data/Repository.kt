package com.example.mvvmlogin.data

import com.example.mvvmlogin.data.local.MedicineDao
import com.example.mvvmlogin.data.model.Drug
import com.example.mvvmlogin.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao
) {

    suspend fun getMedicines(): List<Drug> {
        try {
            // Fetch medicines from the API
            val medicines = apiService.getMedicines()
//            Log.e("response", medicines.toString())

            // Save medicines to Room database
            medicines.drugs?.let { medicineDao.insertMedicines(it) }

            return medicines.drugs
        } catch (e: Exception) {
            // Handle network error
//            Log.e("error api", e.toString())
            throw e
        }
    }

    suspend fun getMedicineDetail(medicineId: String): Drug {
        // Fetch medicine detail from Room database
        return medicineDao.getMedicineDetail(medicineId)
    }
}