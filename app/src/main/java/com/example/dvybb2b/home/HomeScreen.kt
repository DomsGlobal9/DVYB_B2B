package com.example.dvyb.ui.theme.home

import TopBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.dvyb.ui.screens.NotificationScreen
import com.example.dvyb.ui.theme.components.screens.common.BottomBar
import com.example.dvyb.ui.theme.components.screens.common.BottomNavItem
import com.example.dvyb.ui.theme.home.orders.OrdersScreen
//import com.example.dvyb.ui.theme.home.profile.ProfileScreen
import com.example.dvyb2b2.ui.dashboard.DashboardScreen
import com.example.dvybb2b.screens.dashboardComponents.InventoryScreen
import com.example.dvybb2b.screens.dashboardComponents.TotalRevenueScreen
import com.example.dvybb2b.screens.dashboardComponents.TotalUnitsScreen
import com.example.dvybb2b.screens.homecomponents.Sidebar
import androidx.compose.ui.graphics.Color
import com.example.dvybb2b.home.products.ProductDressPreview
import com.example.dvybb2b.home.products.ProductsScreen
import com.example.dvybb2b.home.products.RecentlyAddedProductsScreen
import com.example.dvybb2b.home.profile.Pages.AccountPreferencesScreen
import com.example.dvybb2b.home.profile.Pages.PayoutSettingsScreen
import com.example.dvybb2b.home.profile.ProfileScreen
import com.example.dvybb2b.home.profile.Pages.ShopDetailsScreen
import com.example.dvybb2b.home.profile.Pages.VendorRegisterScreen

import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(navController = navController, onClose = {
                scope.launch {
                    drawerState.close()
                }
            })
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    title = when (currentRoute) {
                        "dashboard" -> "Dashboard"
                        "products" -> "Products"
                        "orders" -> "Your orders"
                        "profile" -> "Profile"
                        else -> "DVYB"
                    },
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            bottomBar = { BottomBar(navController) }
        ) { padding ->
            Surface(
                color = Color(0xFFF2F2F2)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = BottomNavItem.Dashboard.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(BottomNavItem.Dashboard.route) {
                        DashboardScreen(navController)
                    }
                    composable("products") { ProductsScreen(navController)}
                    composable("product_dress_preview/{dressName}") { backStackEntry ->
                        val dressName = backStackEntry.arguments?.getString("dressName") ?: ""
                        ProductDressPreview(dressName = dressName)
                    }
                    composable("recently_added/{gender}") { backStackEntry ->
                        val gender = backStackEntry.arguments?.getString("gender") ?: "Women"
                        RecentlyAddedProductsScreen(gender, navController)
                    }
                    composable("account_preferences") { AccountPreferencesScreen(navController) }
                    composable("vendor_register"){ VendorRegisterScreen(navController) }
                    composable("shop_details") { ShopDetailsScreen(navController) }
                    composable("payout_settings"){ PayoutSettingsScreen(navController) }
                    composable("inventory") { InventoryScreen() }
                    composable("total_revenue") { TotalRevenueScreen() }
                    composable("total_units") { TotalUnitsScreen() }
                    composable("notifications") { NotificationScreen() }
                    composable(BottomNavItem.Products.route) {
                        ProductsScreen(navController)
                    }
                    composable(BottomNavItem.Orders.route) {
                        OrdersScreen(navController)
                    }
                    composable(BottomNavItem.Profile.route) {
                        ProfileScreen(navController)
                    }
                    composable("products") {
                        ProductsScreen(navController)
                    }


                }
            }
        }
    }


}