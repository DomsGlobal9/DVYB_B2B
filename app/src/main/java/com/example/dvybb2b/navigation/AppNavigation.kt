package com.example.dvybb2b.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dvyb.ui.theme.components.screens.login.PasswordResetScreen
import com.example.dvyb.ui.theme.components.screens.splash.SplashScreen
import com.example.dvyb.ui.theme.home.HomeScreen
import com.example.dvyb2b2.ui.dashboard.DashboardScreen
import com.example.dvybb2b.home.products.RecentlyAddedProductsScreen
import com.example.dvybb2b.screens.dashboardComponents.InventoryScreen
import com.example.dvybb2b.screens.OtpEntryScreen
import com.example.dvybb2b.screens.Register.RegisterBankDetailsScreen
import com.example.dvybb2b.screens.Register.RegisterPasswordScreen
import com.example.dvybb2b.screens.Register.RegisterPersonalDetails
import com.example.dvybb2b.screens.login.LoginScreen
import com.example.dvybb2b.screens.login.OtpScreen
import com.example.dvybb2b.ui.theme.screens.onboarding.OnboardingScreen
import com.example.dvybb2b.ui.theme.screens.register.RegisterShopDetailsScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("onboarding/{step}",
            arguments = listOf(navArgument("step") { type = NavType.IntType })
        ) {
            val step = it.arguments?.getInt("step") ?: 0
            OnboardingScreen(navController, step) }
        composable("login") { LoginScreen(navController) }
        composable("otp") { OtpScreen(navController) }
        composable("forgot_password") { PasswordResetScreen(navController) }
        composable("home") { HomeScreen() }
        composable("registerDetails") { RegisterPersonalDetails(navController) }
        composable("shopDetails"){ RegisterShopDetailsScreen(navController)}
        composable("bankDetails"){ RegisterBankDetailsScreen(navController,  onHelp = { /* handle help */ }) }
        composable("passwordAndOtp"){ RegisterPasswordScreen(navController) }
        composable("otpEntry"){OtpEntryScreen(navController)}
    }
}

