package com.example.mvvmlogin

import com.example.mvvmlogin.data.Repository
import com.example.mvvmlogin.data.local.MedicineDao
import com.example.mvvmlogin.data.model.Drug
import com.example.mvvmlogin.data.model.Medicine
import com.example.mvvmlogin.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var medicineDao: MedicineDao


    private lateinit var medicineRepository: Repository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        medicineRepository = Repository(apiService,medicineDao)
    }

    @Test
    fun fetchMedicines_success() = runBlocking {
        // Given
        val expectedMedicines = listOf(
            Drug(1, "Medicine1", "Dose1", "Strength1"),
            Drug(2, "Medicine2", "Dose2", "Strength2")
        )
        `when`(apiService.getMedicines()).thenReturn(Medicine(expectedMedicines))

        // When
        val result = medicineRepository.getMedicines()

        // Then
        assertEquals(expectedMedicines,result)
    }

    @Test
    fun fetchMedicines_emptyList() = runBlocking {
        // Given
        val expectedMedicines = emptyList<Drug>()

        `when`(apiService.getMedicines()).thenReturn(
            Medicine(emptyList())
        )

        // When
        val result = medicineRepository.getMedicines()

        // Then
        assertEquals(expectedMedicines, result)
    }

    @Test(expected = Exception::class)
    fun fetchMedicines_error(): Unit = runBlocking {
        // Given
        `when`(apiService.getMedicines()).thenThrow(Exception("Failed to fetch medicines"))

        // When
        medicineRepository.getMedicines()

        // Then
        // Exception is expected
    }
}