package com.example.dvyb.ui.theme.home.orders



import com.example.dvyb.ui.theme.home.orders.model.Order
import com.example.dvyb.ui.theme.home.orders.model.OrderItem
import com.example.dvyb.ui.theme.home.orders.model.TrackingStatus

class OrdersRepository {
    fun getMockOrders(): List<Order> {
        return listOf(
            Order(
                orderId = "456765",
                productId = "P001",
                productImage = "https://via.placeholder.com/150",
                customerName = "John Doe",
                shippingAddress = "123, Main Street, NY",
                itemCount = 3,
                items = listOf(
                    OrderItem("Lehenga Choli", "https://via.placeholder.com/60",2),
                    OrderItem("Silk Saree", "https://via.placeholder.com/60",3),
                    OrderItem("Punjabi set", "https://via.placeholder.com/60",5),
                ),
                tracking = TrackingStatus(true, true, true, true)
            ),
            Order(
                orderId = "454569",
                productId = "P002",
                productImage = "https://via.placeholder.com/150",
                customerName = "Jane Smith",
                shippingAddress = "456, Lakeview, CA",
                itemCount = 2,
                items = listOf(
                    OrderItem("Lehenga Choli", "https://via.placeholder.com/60",5),
                    OrderItem("Silk Saree", "https://via.placeholder.com/60",2),
                    OrderItem("Punjabi set", "https://via.placeholder.com/60",1),
                ),
                tracking = TrackingStatus(true, true, true, false)
            ),
            Order(
                orderId = "454809",
                productId = "P003",
                productImage = "https://via.placeholder.com/150",
                customerName = "Mike Lee",
                shippingAddress = "789, Ocean Blvd, FL",
                itemCount = 1,
                items = listOf(
                    OrderItem("Lehenga Choli", "https://via.placeholder.com/60",4),
                    OrderItem("Silk Saree", "https://via.placeholder.com/60",4),
                    OrderItem("Punjabi set", "https://via.placeholder.com/60",2),
                ),
                tracking = TrackingStatus(true, true, false, false)
            ),
            Order(
                orderId = "454806",
                productId = "P003",
                productImage = "https://via.placeholder.com/150",
                customerName = "Mike Lee",
                shippingAddress = "789, Ocean Blvd, FL",
                itemCount = 1,
                items = listOf(
                    OrderItem("Lehenga Choli", "https://via.placeholder.com/60",3),
                    OrderItem("Silk Saree", "https://via.placeholder.com/60",7),
                    OrderItem("Punjabi set", "https://via.placeholder.com/60",8),
                ),
                tracking = TrackingStatus(true, true, false, false)
            ),
            Order(
                orderId = "4542109",
                productId = "P003",
                productImage = "https://via.placeholder.com/150",
                customerName = "Mike Lee",
                shippingAddress = "789, Ocean Blvd, FL",
                itemCount = 1,
                items = listOf(
                    OrderItem("Lehenga Choli", "https://via.placeholder.com/60",7),
                    OrderItem("Silk Saree", "https://via.placeholder.com/60",2),
                    OrderItem("Punjabi set", "https://via.placeholder.com/60",2),
                ),
                tracking = TrackingStatus(false, true, false, false)
            ),
        )
    }
}
