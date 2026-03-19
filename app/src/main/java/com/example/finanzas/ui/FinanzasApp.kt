package com.example.finanzas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.finanzas.ui.components.FinanzasTab
import com.example.finanzas.ui.components.FinanzasTabBar
import com.example.finanzas.ui.screens.AccountsScreen
import com.example.finanzas.ui.screens.BillsScreen
import com.example.finanzas.ui.screens.FinanzasScreen

@Composable
fun FinanzasApp() {
    var currentTab by remember { mutableStateOf(FinanzasTab.OVERVIEW) }

    Scaffold(
        modifier = Modifier.fillMaxSize().safeDrawingPadding(),
        topBar = {
            FinanzasTabBar(
                allTabs = FinanzasTab.entries,
                currentTab = currentTab,
                onTabSelected = { currentTab = it }
            )
        }
    ) { paddingValues ->
        when (currentTab) {
            FinanzasTab.OVERVIEW -> FinanzasScreen(modifier = Modifier.padding(paddingValues))
            FinanzasTab.ACCOUNTS -> AccountsScreen(modifier = Modifier.padding(paddingValues))
            FinanzasTab.BILLS -> BillsScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}