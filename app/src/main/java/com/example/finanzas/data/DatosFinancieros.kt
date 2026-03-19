package com.example.finanzas.data

import androidx.compose.ui.graphics.Color
import com.example.finanzas.ui.theme.*

data class CuentaBancaria(
    val nombre: String,
    val numero: String,
    val balance: Float,
    val color: Color
)

object ProveedorDatos {
    val cuentas = listOf(
        CuentaBancaria("Cuenta Corriente", "345678", 2500f, ColorCuentaCorriente),
        CuentaBancaria("Ahorros Casa", "123456", 1500f, ColorAhorros),
        CuentaBancaria("Inversiones ETF", "987654", 3000f, ColorInversiones),
        CuentaBancaria("Tarjeta de Crédito", "456789", 800f, ColorTarjetas),
        CuentaBancaria("Reserva Impuestos", "112233", 500f, ColorImpuestos)
    )
}
