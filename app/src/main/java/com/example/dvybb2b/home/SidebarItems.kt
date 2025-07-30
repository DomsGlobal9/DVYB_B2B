package com.example.dvybb2b.home
import androidx.annotation.DrawableRes
import com.example.dvybb2b.R

sealed class SidebarItem(val title: String, @DrawableRes val icon: Int, val route: String) {
    object Dashboard : SidebarItem("Dashboard", R.drawable.sidebar1, "dashboard")
    object Products : SidebarItem("Products", R.drawable.sidebar2, "products")
    object Orders : SidebarItem("Orders", R.drawable.sidebar3, "orders")
    object Profile : SidebarItem("Profile", R.drawable.sidebar4, "profile")
    object Logout : SidebarItem("Logout", R.drawable.logout, "logout")
}
