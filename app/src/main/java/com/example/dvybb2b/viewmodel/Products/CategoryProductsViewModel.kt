package com.example.dvybb2b.viewmodel.Products


import androidx.lifecycle.ViewModel
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Product.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CategoryProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun loadProductsForCategory(category: String) {
        val dummyProducts = when (category) {
            "Women_TShirts" -> listOf(
                Product(1, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(2, "pink T-Shirt", "₹799", "Allproducts/pink_tshirt.png", "185 reviews"),
                Product(3, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(4, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(5, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(6, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(7, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(8, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),

                )
            "Women_Skirts" -> listOf(
                Product(1, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(2, "pink T-Shirt", "₹799", "Allproducts/pink_tshirt.png", "185 reviews"),
                Product(3, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(4, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(5, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(6, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(7, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(8, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),

                )

            "Men_TShirts" -> listOf(
                Product(1, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(2, "pink T-Shirt", "₹799", "Allproducts/pink_tshirt.png", "185 reviews"),
                Product(3, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(4, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(5, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(6, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(7, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),
                Product(8, "White T-Shirt", "₹699", "Allproducts/white_tshirt.png", "220 reviews"),

                )

            "Men_Jeans" -> listOf(
                Product(1, "Blue Jeans", "₹1299", "", "178 reviews"),
                Product(2, "Black Jeans", "₹1399","", "145 reviews"),
                Product(3, "Blue Jeans", "₹1299", "", "178 reviews"),
                Product(4, "Blue Jeans", "₹1299", "", "178 reviews"),
                Product(5, "Blue Jeans", "₹1299", "", "178 reviews")
            )
//            "Infant_Bodysuits" -> listOf(
//                Product(1, "Pink Bodysuit", "₹499", R.drawable.pink_bodysuit, "112 reviews"),
//                Product(2, "Blue Bodysuit", "₹549", R.drawable.blue_bodysuit, "120 reviews")
//            )
//            "Kids_Boys_Sweatshirts" -> listOf(
//                Product(1, "Green Sweatshirt", "₹799", R.drawable.green_sweatshirt, "98 reviews"),
//                Product(2, "Navy Sweatshirt", "₹849", R.drawable.navy_sweatshirt, "110 reviews")
//            )
//            "Kids_Girls_Frocks" -> listOf(
//                Product(1, "Red Frock", "₹999", R.drawable.red_frock, "132 reviews"),
//                Product(2, "Yellow Frock", "₹899", R.drawable.yellow_frock, "120 reviews")
//            )
            else -> emptyList()
        }
        // Replace with repository call if needed

        _products.value = dummyProducts
    }
}
