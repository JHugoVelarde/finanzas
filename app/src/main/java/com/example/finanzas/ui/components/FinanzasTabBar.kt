package com.example.finanzas.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp

enum class FinanzasTab(val title: String, val icon: ImageVector) {
    OVERVIEW("Resumen", Icons.Filled.PieChart),
    ACCOUNTS("Cuentas", Icons.Filled.AttachMoney),
    BILLS("Gastos", Icons.Filled.MoneyOff)
}

@Composable
fun FinanzasTabBar(
    allTabs: List<FinanzasTab>,
    currentTab: FinanzasTab,
    onTabSelected: (FinanzasTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            allTabs.forEach { tab ->
                FinanzasTabItem(
                    text = tab.title,
                    icon = tab.icon,
                    onSelected = { onTabSelected(tab) },
                    selected = currentTab == tab
                )
            }
        }
    }
}

@Composable
private fun FinanzasTabItem(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "tabColor"
    )

    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .clearAndSetSemantics { contentDescription = text },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = color)
        if (selected) {
            Spacer(Modifier.width(12.dp))
            Text(text = text.uppercase(), color = color, style = MaterialTheme.typography.labelLarge)
        }
    }
}