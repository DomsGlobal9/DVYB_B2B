package com.example.dvybb2b.viewmodel.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*




// DashboardViewModel.kt
class DashboardViewModel : ViewModel() {

    private val _currentDate = mutableStateOf("")
    val currentDate: State<String> = _currentDate

    init {
        generateCurrentDate()
    }

    private fun generateCurrentDate() {
        val today = LocalDate.now()
        val date = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val dayOfWeek = today.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        _currentDate.value = "$date\n$dayOfWeek"
    }


    // Simple hardcoded data (replace with real data later)
    private val _dashboardData = mutableStateOf(
        DashboardData(
            productCount = 120,
            inventoryCount = 2345,
            totalRevenue = "₹12,000",
            unitsSold = 1300,
            salesData = SalesData(
                months = listOf("May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan"),
                heights = listOf(60, 20, 60, 40, 60, 60, 20, 100, 60),
                highMonth = "Dec",
                highValue = "₹0",
                lowMonth = "Jun",
                lowValue = "₹0",
                lastUpdated = "Jan 26, 2024, 10 AM"
            )
        )
    )
    val dashboardData: State<DashboardData> = _dashboardData

    private val _selectedTab = mutableStateOf("Monthly")
    val selectedTab: State<String> = _selectedTab

    fun selectTab(tab: String) {
        _selectedTab.value = tab
    }
}

// Simple data classes
data class DashboardData(
    val productCount: Int,
    val inventoryCount: Int,
    val totalRevenue: String,
    val unitsSold: Int,
    val salesData: SalesData
)

data class SalesData(
    val months: List<String>,
    val heights: List<Int>,
    val highMonth: String,
    val highValue: String,
    val lowMonth: String,
    val lowValue: String,
    val lastUpdated: String
)

