package com.example.myapplication.ui.theme.navigations


sealed class SupportScreens(val route: String) {
    object Support : SupportScreens("support")
    object FAQ : SupportScreens("faq")
    object LiveChat : SupportScreens("live_chat")
}