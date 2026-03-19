package com.example.finanzas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finanzas.data.ProveedorDatos
import com.example.finanzas.ui.components.AccountRow
import com.example.finanzas.ui.components.AnimatedDonutChart
import com.example.finanzas.ui.theme.FinanzasBackground

@Composable
fun FinanzasScreen(modifier: Modifier = Modifier) {
    val cuentas = ProveedorDatos.cuentas

    Surface(modifier = modifier.fillMaxSize(), color = FinanzasBackground) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AnimatedDonutChart(
                    cuentas = cuentas,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
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