package com.example.finanzas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finanzas.data.CuentaBancaria
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AccountRow(cuenta: CuentaBancaria, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador de color lateral
        Spacer(
            modifier = Modifier
                .width(4.dp)
                .height(36.dp)
                .background(cuenta.color)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = cuenta.nombre, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "•••• ${cuenta.numero.takeLast(4)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        val formateador = NumberFormat.getCurrencyInstance(Locale.US)
        Text(
            text = formateador.format(cuenta.balance),
            style = MaterialTheme.typography.titleMedium
        )
    }
}