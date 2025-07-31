package com.example.dvybb2b.model.Product

import com.example.dvybb2b.R


data class ProductCategory(
    val name: String,
    val count: Int
)

data class CategoryWise(
    val name: String,
    val count: Int,
    val imageRes: Int
)

data class ProductPerformance(
    val name: String,
    val imageRes: Int
)

data class ProductOverview(
    val totalCount: Int,
    val lastUpdated: String,
)

data class Products(
    val id: Int,
    val title: String,
    val price: String,
    val imageAssetPath: String,
    val reviews: String
)


data class Product(val name: String, val image: Int) {
    val imageAssetPath: String = ""
}

val productsMap = mapOf(
    "Women" to mapOf(
        "Top Wear" to listOf(
            Product("T-Shirts", R.drawable.tshirt),
            Product("Tops & Blouses", R.drawable.tops),
            Product("Shirts", R.drawable.shirts),
            Product("Kurtis & Kurtas", R.drawable.kurth1),
            Product("Tunics", R.drawable.tunics),
            Product("Tank Tops", R.drawable.tanktops),
            Product("Crop Tops", R.drawable.croptop),
            Product("Camisoles", R.drawable.camisoles)
        ),
        "Bottom Wear" to listOf(
            Product("Jeans", R.drawable.jeans),
            Product("Trousers & Pants", R.drawable.trouser),
            Product("Leggings", R.drawable.leggins),
            Product("Palazzos", R.drawable.palazo),
            Product("Skirts", R.drawable.skirts),
            Product("Shorts", R.drawable.shorts),
            Product("Jeggings", R.drawable.jeggins),
            Product("Culottes", R.drawable.culotes),
            Product("Dhoti Pants", R.drawable.dhotipants)
        ),
        "Ethnic Wear" to listOf(
            Product("Sarees", R.drawable.saree),
            Product("Salwar Suits", R.drawable.salwar),
            Product("Lehengas", R.drawable.lehanga),
            Product("Anarkalis", R.drawable.anarkali),
            Product("Dupattas", R.drawable.dupattas),
            Product("Ethnic Jackets", R.drawable.ethnicjackets),
            Product("Gowns Ethnic", R.drawable.gownsethnic)
        ),
        "Dresses & Jumpsuits" to listOf(
            Product("Maxi Dresses", R.drawable.maxidress),
            Product("Midi Dresses", R.drawable.midi),
            Product("Bodycon Dresses", R.drawable.bodycorn),
            Product("A-line Dresses", R.drawable.aline),
            Product("Jumpsuits", R.drawable.jumpsuits),
            Product("Rompers", R.drawable.rompers)
        ),
        "Loungewear & Sleepwear" to listOf(
            Product("Night Suits", R.drawable.nightsuits),
            Product("Nighties", R.drawable.nighties),
            Product("Pyjamas", R.drawable.pyjamas),
            Product("Loungewear Sets", R.drawable.loungewear),
            Product("Robes", R.drawable.robes)
        ),
        "Winterwear" to listOf(
            Product("Sweaters", R.drawable.sweaters),
            Product("Cardigans", R.drawable.cardigans),
            Product("Sweatshirts", R.drawable.sweatshirts),
            Product("Jackets", R.drawable.jacket),
            Product("Coats", R.drawable.coat),
            Product("Shawls", R.drawable.shawals),
            Product("Ponchos", R.drawable.ponchos)
        ),
        "Active Wear" to listOf(
            Product("Sports Bra", R.drawable.sportsbra),
            Product("Track Pants", R.drawable.trackpants),
            Product("Yoga Leggings", R.drawable.leggins),
            Product("Workout Tshirts", R.drawable.tshirt),
            Product("Joggers", R.drawable.joggers),
        ),
        "Inner Wear" to listOf(
            Product("Bras", R.drawable.sportsbra),
            Product("Panties", R.drawable.shorts),
            Product("Slips & Camisoles", R.drawable.camisoles),
            Product("Shape Wear", R.drawable.bodycorn),
        ),
        "Maternity Wear" to listOf(
            Product("Maternity Dresses", R.drawable.kurthi),
            Product("Feeding Tops", R.drawable.cardigans),
            Product("Maternity Leggins", R.drawable.leggins),
        )
        // ... continue rest from your existing list
    ),
    "Men" to mapOf(
        "Top Wear" to listOf(
            Product("T-Shirts", R.drawable.tshirt),
            Product("Casual Shirts", R.drawable.shirts),
            Product("Formal Shirts", R.drawable.formalshirt),
            Product("Polos", R.drawable.polos),
            Product("SweatShirts", R.drawable.sweatshirts),
            Product("Kurtas", R.drawable.kurtas),
        ),
        "Bottom Wear" to listOf(
            Product("Jeans", R.drawable.jeans),
            Product("Trousers", R.drawable.trouser),
            Product("Shorts", R.drawable.shorts),
            Product("Track Pants", R.drawable.trackpants),
            Product("Cargos", R.drawable.cargos),
            Product("chinos", R.drawable.cargoas),
            Product("Dhotis & Pajamas", R.drawable.dhoti),
        ),
        "Ethnic Wear" to listOf(
            Product("Kurta Sets", R.drawable.kurtas),
            Product("Sherwanis", R.drawable.dhoti),
            Product("Nehru Jackets", R.drawable.nehrujackets),
            Product("Pathani Suits", R.drawable.salwar),
        ),
        "Loungewear & Innerwear" to listOf(
            Product("Boxers", R.drawable.shorts),
            Product("vests", R.drawable.vests),
            Product("Lounge wear sets", R.drawable.loungewear)
        ),
        "Activewear" to listOf(
            Product("Gym Tshirts", R.drawable.tshirt),
            Product("Running Shorts", R.drawable.shorts),
            Product("Sports Jackets", R.drawable.jacket),
            Product("Joggers", R.drawable.joggers),
        ),
        "Winterwear" to listOf(
            Product("Sweaters", R.drawable.sweaters),
            Product("Jackets", R.drawable.jacket),
            Product("Hoodies", R.drawable.hoodie),
            Product("Blazers", R.drawable.coat),
            Product("Thermal Wear", R.drawable.loungewear),
        )
        // ... continue
    ),
    "Kids" to mapOf(
        "Boys" to listOf(
            Product("T-Shirts", R.drawable.tshirt),
            Product("Shorts", R.drawable.shorts),
            Product("Shirts", R.drawable.shirts),
            Product("Ethnic Wear", R.drawable.kidethnic),
            Product("SweatShirts & Jackets", R.drawable.sweatshirts),
            Product("NightWear", R.drawable.nightsuits),
        ),
        "Girls" to listOf(
            Product("Frocks", R.drawable.gownsethnic),
            Product("Skirts", R.drawable.skirts),
            Product("Tops & Tshirts", R.drawable.tshirt),
            Product("Jeans & Leggings", R.drawable.jeans),
            Product("Ethnic wear", R.drawable.kurthi),
            Product("Night wear", R.drawable.nightsuits),
            Product("Sweaters & Jackets", R.drawable.sweaters),
        ),
        "Infants" to listOf(
            Product("Body Suits", R.drawable.infantsuit),
            Product("SleepSuits", R.drawable.sleepsuitsinfants),
            Product("Frocks", R.drawable.frok),
            Product("Body Sets", R.drawable.suitinfants),
            Product("Diaper Pants", R.drawable.diperpants),
            Product("Thermel Wear", R.drawable.thermalwear),

            )
        // ... continue
    )
)
