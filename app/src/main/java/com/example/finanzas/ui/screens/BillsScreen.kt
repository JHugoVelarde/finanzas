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
import com.example.finanzas.ui.components.AnimatedBarChart
import com.example.finanzas.ui.theme.FinanzasBackground

@Composable
fun BillsScreen(modifier: Modifier = Modifier) {
    val cuentas = ProveedorDatos.cuentas
    // Simulamos los gastos tomando una fracción del balance y sus colores
    val gastos = cuentas.map { it.balance * 0.4f }
    val colores = cuentas.map { it.color }

    Surface(modifier = modifier.fillMaxSize(), color = FinanzasBackground) {
        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Gastos del Mes",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    AnimatedBarChart(
                        values = gastos,
                        colors = colores,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            items(cuentas) { cuenta ->
                // Reutilizamos AccountRow para simular la lista de facturas
                AccountRow(cuenta = cuenta.copy(balance = cuenta.balance * 0.4f))
                HorizontalDivider(
                    modifier = Modifier.padding(start = 36.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )
            }
        }
    }
}