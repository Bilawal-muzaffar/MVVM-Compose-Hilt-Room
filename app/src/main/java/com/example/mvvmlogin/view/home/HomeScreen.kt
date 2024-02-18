package com.example.mvvmlogin.view.home
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmlogin.data.model.Drug
import com.example.mvvmlogin.view.login.LoginViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun HomeScreen(viewModel: LoginViewModel = viewModel(),
               onMedicineCardClick: (Drug) -> Unit) {
    val name by viewModel.userGreeting.collectAsState()
    val currentTime = getCurrentTime()
    val greetingMessage = getGreetingMessage(currentTime)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = greetingMessage + name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn {
            items(viewModel.medicines.value) { medicine ->
                MedicineCard(drug = medicine, onClick = { onMedicineCardClick(medicine) })
            }
        }
    }
}

@Composable
fun MedicineCard(
    drug: Drug,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = drug.name, style = MaterialTheme.typography.titleSmall)
            Text(text = "Dose: ${drug.dose}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Strength: ${drug.strength}", style = MaterialTheme.typography.titleSmall)
        }
    }

}

fun getGreetingMessage(currentTime: String): String {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val currentTimeFormatted = timeFormat.parse(currentTime)

    return when {
        currentTimeFormatted.before(timeFormat.parse("12:00")) -> "Good morning! "
        currentTimeFormatted.before(timeFormat.parse("18:00")) -> "Good afternoon! "
        else -> "Good evening! "
    }
}
fun getCurrentTime(): String {
    val currentTime = Calendar.getInstance().time
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(currentTime)
}