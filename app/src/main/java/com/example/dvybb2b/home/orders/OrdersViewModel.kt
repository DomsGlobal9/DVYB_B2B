////package com.example.dvyb.ui.theme.home.orders
////
////
////import androidx.lifecycle.ViewModel
////import androidx.lifecycle.viewModelScope
////import com.example.dvyb.ui.theme.home.orders.model.Order
////import kotlinx.coroutines.flow.MutableStateFlow
////import kotlinx.coroutines.flow.StateFlow
////import kotlinx.coroutines.launch
////
////class OrdersViewModel : ViewModel() {
////    private val repository = OrdersRepository()
////
////    private val _orders = MutableStateFlow<List<Order>>(emptyList())
////    val orders: StateFlow<List<Order>> = _orders
////
////    init {
////        fetchOrders()
////    }
////
////    private fun fetchOrders() {
////        viewModelScope.launch {
////            _orders.value = repository.getMockOrders()
////        }
////    }
////}
////
////
//
//package com.example.dvyb.ui.theme.home.orders
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.dvyb.ui.theme.home.orders.model.Order
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class OrdersViewModel : ViewModel() {
//    private val repository = OrdersRepository()
//
//    private val _orders = MutableStateFlow<List<Order>>(emptyList())
//    val orders: StateFlow<List<Order>> = _orders
//
//    init {
//        fetchOrders()
//    }
//
//    private fun fetchOrders() {
//        viewModelScope.launch {
//            _orders.value = repository.getMockOrders()
//        }
//    }
//
//    fun getOrderById(orderId: String): Order? {
//        return _orders.value.find { it.orderId == orderId }
//    }
//}

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dvyb.ui.theme.home.orders.OrdersRepository
import com.example.dvyb.ui.theme.home.orders.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrdersViewModel : ViewModel() {
    private val repository = OrdersRepository()

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            _orders.value = repository.getMockOrders()
        }
    }

//    fun getOrderById(orderId: String): Order? {
//        return _orders.value.find { it.orderId == orderId }
//    }
fun getOrderById(orderId: String): Order? {
    return _orders.value.find { it.orderId == orderId }
}

}
