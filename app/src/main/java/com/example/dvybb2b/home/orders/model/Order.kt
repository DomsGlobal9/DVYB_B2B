package com.example.dvyb.ui.theme.home.orders.model


data class Order(
    val orderId: String,
    val productId: String,
    val productImage: String,
    val customerName: String,
    val shippingAddress: String,
    val itemCount: Int,
    val items: List<OrderItem>,
    val tracking: TrackingStatus
)
data class OrderItem(
    val name: String,
    val image: String,
    val quantity: Int
)

data class TrackingStatus(
    val isPlaced: Boolean,
    val isConfirmed: Boolean,
    val isShipped: Boolean,
    val isDelivered: Boolean
)

