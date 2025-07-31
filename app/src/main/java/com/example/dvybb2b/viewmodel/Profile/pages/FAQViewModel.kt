

package com.example.myapplication.ui.theme.Support.ViewModel

import androidx.lifecycle.ViewModel

data class FAQItem(
    val question: String,
    val answer: String
)

class FAQViewModel : ViewModel() {
    val faqItems = listOf(
        FAQItem(
            question = "How do I manage my notifications?",
            answer = "To manage notifications, go to \"Settings\" select Notification Settings and customize your preferences."
        ),
        FAQItem(
            question = "How do I reset my password?",
            answer = "Go to Account Settings > Security > Change Password and follow the instructions."
        ),
        FAQItem(
            question = "What payment methods do you accept?",
            answer = "We accept all major credit cards, PayPal, and bank transfers."
        ),
        FAQItem(
            question = "How can I track my order?",
            answer = "You can track your order from the Orders section in your account or via the tracking link in your confirmation email."
        ),
        FAQItem(
            question = "What is your return policy?",
            answer = "We accept returns within 30 days of purchase. Items must be unused and in original packaging."
        )
    )
}