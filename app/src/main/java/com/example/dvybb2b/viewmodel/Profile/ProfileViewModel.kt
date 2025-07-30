package com.example.dvybb2b.viewmodel.Profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Profile.UserProfile


class ProfileViewModel : ViewModel() {

    private val _userProfile = mutableStateOf(
        UserProfile(
            name = "Sagar vardhan",
            phone = "9294294299",
            imageRes = R.drawable.profile // Your local drawable image
        )
    )

    val userProfile = _userProfile

    // Add update/edit logic if needed
}