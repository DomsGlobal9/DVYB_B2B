package com.example.dvybb2b.navigation


sealed class SupportScreens(val route: String) {
    object Support : SupportScreens("support")
    object FAQ : SupportScreens("faq")
    object LiveChat : SupportScreens("live_chat")
}