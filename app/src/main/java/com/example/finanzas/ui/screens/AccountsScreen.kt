package com.example.finanzas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finanzas.data.ProveedorDatos
import com.example.finanzas.ui.components.AccountRow
import com.example.finanzas.ui.components.AnimatedLineChart
import com.example.finanzas.ui.theme.FinanzasBackground
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AccountsScreen(modifier: Modifier = Modifier) {
    val cuentas = ProveedorDatos.cuentas
    val totalBalance = cuentas.sumOf { it.balance.toDouble() }
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)

    // Datos históricos simulados para trazar la curva
    val historyData = listOf(1500f, 2200f, 1800f, 3100f, 2800f, 4500f, 4200f, 5000f, 4800f, totalBalance.toFloat())

    Surface(modifier = modifier.fillMaxSize(), color = FinanzasBackground) {
        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Balance Total",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = formatter.format(totalBalance),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    AnimatedLineChart(data = historyData)
                }
            }

            items(cuentas) { cuenta ->
                AccountRow(cuenta = cuenta)
                HorizontalDivider(
                    modifier = Modifier.padding(start = 36.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )
            }
        }
    }
}