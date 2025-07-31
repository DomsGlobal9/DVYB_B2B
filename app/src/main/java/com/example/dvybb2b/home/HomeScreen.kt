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
import com.example.dvyb2b2.ui.dashboard.DashboardScreen
import com.example.dvybb2b.screens.dashboardComponents.InventoryScreen
import com.example.dvybb2b.screens.dashboardComponents.TotalRevenueScreen
import com.example.dvybb2b.screens.dashboardComponents.TotalUnitsScreen
import com.example.dvybb2b.screens.homecomponents.Sidebar
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dvyb.ui.theme.home.orders.OrderItemScreen
import com.example.dvyb.ui.theme.home.orders.OrdersViewModel
import com.example.dvyb.ui.theme.home.profile.pages.AccountPreferencesScreen
import com.example.dvybb2b.home.products.AddProductScreen
import com.example.dvybb2b.home.products.CategoryDetailScreen
import com.example.dvybb2b.home.products.EmptyScreen
import com.example.dvybb2b.home.products.PreviewScreen
import com.example.dvybb2b.home.products.ProductDressPreview
import com.example.dvybb2b.home.products.ProductsScreen
import com.example.dvybb2b.home.products.RecentlyAddedProductsScreen
import com.example.dvybb2b.home.PayoutSettingsScreen
import com.example.dvybb2b.home.profile.Pages.FAQScreen
import com.example.dvybb2b.home.profile.Pages.LiveChatScreen
import com.example.dvybb2b.home.profile.ProfileScreen
import com.example.dvybb2b.home.profile.Pages.ShopDetailsScreen
import com.example.dvybb2b.home.profile.Pages.VendorRegisterScreen
import com.example.dvybb2b.navigation.SupportScreens
import com.example.dvybb2b.viewmodel.TotalProductsScreen
import com.example.myapplication.ui.theme.Support.SupportScreen


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
                    composable(BottomNavItem.Dashboard.route) { DashboardScreen(navController) }
                    composable("products") { ProductsScreen(navController)}
                    composable("totalProducts") { TotalProductsScreen(navController = navController) }
                    composable(
                        route = "categoryDetail/{gender}/{categoryName}",
                        arguments = listOf(
                            navArgument("gender") { type = NavType.StringType },
                            navArgument("categoryName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val gender = backStackEntry.arguments?.getString("gender") ?: "Women"
                        val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                        CategoryDetailScreen(categoryName = categoryName, gender = gender)
                    }
                    composable("add_product") {
                        AddProductScreen(navController)
                    }
                    composable("product_preview/{productId}") { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        PreviewScreen(navController, productId)
                    }
                    composable("product_dress_preview/{productId}") { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        productId?.let {
                            ProductDressPreview(dressId = it) // we'll update the composable next
                        }
                    }

                    composable("recently_added/{gender}") { backStackEntry ->
                        val gender = backStackEntry.arguments?.getString("gender") ?: "Women"
                        RecentlyAddedProductsScreen(gender, navController)
                    }
                    composable("account_preference") { AccountPreferencesScreen() }
                    composable("vendor_register"){ VendorRegisterScreen(navController) }
                    composable("shop_details") { ShopDetailsScreen(navController) }
                    composable("payout_settings"){ PayoutSettingsScreen(navController) }
                    composable("inventory") { InventoryScreen() }
                    composable("total_revenue") { TotalRevenueScreen() }
                    composable("total_units") { TotalUnitsScreen() }
                    composable("notifications") { NotificationScreen() }
                    composable("orders") {
                        val viewModel: OrdersViewModel = viewModel()
                        OrdersScreen(navController, viewModel)
                    }
                    composable("is_empty"){ EmptyScreen(navController) }
                    composable(
                        "orderDetails/{orderId}",
                        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
                    ) {
                        val orderId = it.arguments?.getString("orderId") ?: ""
                        val viewModel: OrdersViewModel = viewModel()
                        OrderItemScreen(
                            modifier = Modifier,
                            orderId = orderId,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable(SupportScreens.Support.route) {
                        SupportScreen(navController)
                    }

                    composable(SupportScreens.FAQ.route) {
                        FAQScreen(navController )
                    }

                    composable(SupportScreens.LiveChat.route) {
                        LiveChatScreen(navController)
                    }
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