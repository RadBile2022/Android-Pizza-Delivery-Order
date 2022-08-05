package com.example.ujkradar.data


import com.example.ujkradar.R
import com.example.ujkradar.models.ItemMenu

object DataMenu {
    private val menuMakananImage = arrayListOf<Int>(
        R.drawable.a1_frankfurter,
        R.drawable.a2_cheeseburger,
        R.drawable.a3_meatmonsta,
        R.drawable.a4_supreme,
        R.drawable.a5_meatlover,
        R.drawable.a6_american
    )

    private val menuMakananNama = arrayListOf<String>(
        "FRANKFURTER BBQ", "CHEESEBURGER","MEAT MONSTA",
        "SUPER SUPREME", "MEAT LOVER", "AMERICAN FAVOURITE"
    )

    private val menuMakananHarga = arrayListOf<Int>(
        47000,62000,118000,135000,169000,185000
    )

    private val menuMakananDesc = arrayOf(
        "Frangfurter Sapi, Daging Sapi Cinca, dan Keju Mozzarella",
        "Daging Sapi Cincang Berbumbu, Keju Cheddar, Saus Mustard dan Keju Mozzarella",
        "Farankfurter Sapi, Daging Sapi Asap, Daging Sapi Cincang, Jamur, dan Keju Mozzarella",
        "Daging Sapi Asap, Daging Ayam Asap, Burger Sapi, Daging Sapi Cincang, dan Keju Mozzarella",
        "Sosis Sapi, Daging Sapi Cincang, Burger Sapi, Sosis Ayam dan Keju Mozzarella",
        "Peeperoni Sapi, Daging Sapi Cincang, Jamur dan Keju Mozzarella"

    )

    val listMenuMakanan: ArrayList<ItemMenu>
    get() {
        val list = arrayListOf<ItemMenu>()
        for (item in menuMakananNama.indices) {
            val addMakanan = ItemMenu(
                menuMakananNama[item],
                menuMakananDesc[item],
                menuMakananHarga[item],
                menuMakananImage[item])
            list.add(addMakanan)
        }
        return list
    }

    private val menuMinumanImage = intArrayOf(
        R.drawable.b1_tropical,
        R.drawable.b2_lyche,
        R.drawable.b3_orange,
        R.drawable.b4_blue,
        R.drawable.b5_mixed,
        R.drawable.b6_winter
    )

    private val menuMinumanNama = arrayOf(
        "TROPICAL PUNCH", "LYCHEE BREEZE", "ORANGE LYCHE SPARKLE",
        "BLUE OCEAN", "MIXBERRY","WINTER PUNCH"
    )

    private val menuMinumanHarga = intArrayOf(
        33000,33000,31000,31000,31000,33000
    )

    private val menuMinumanDesc = arrayOf(
        "Buah PEach, Nata de Coco, Semangka, Biji Selasaih, Sirup Mangga dan Lemon Tea",
        "Buah Leci, Sirup Leci, Soda dan Lemon Tea",
        "Jus Jeruk dan Sirup Leci",
        "Soda Bluberi, Sirup Mangga dan Biji Selasih",
        "Soda Bluberi, Sirup Stroberi, Biji Selasih dan Jelly",
        "Buah Leci, Nata de Coco, Biji Selasih, Sirup Leci dan Lemon Tea"
    )

    val listMenuMinuman: ArrayList<ItemMenu>
        get() {
            val list = arrayListOf<ItemMenu>()
            for (item in menuMinumanNama.indices) {
                val addMakanan = ItemMenu(
                    menuMinumanNama[item],
                    menuMinumanDesc[item],
                    menuMinumanHarga[item],
                    menuMinumanImage[item])
                list.add(addMakanan)
            }
            return list
        }

    private val menuDessertImage = intArrayOf(
        R.drawable.c1_orleans,
        R.drawable.c2_backed,
        R.drawable.c3_sausage,
        R.drawable.c4_garlic,
        R.drawable.c5_chicken,
        R.drawable.c6_nacho
    )

    private val menuDessertNama = arrayOf(
        "NEW ORLEANS CHICKEN WINGS" ,"BAKED CHICKEN CHUNKS", "SAUSAGE PASTRY ROLL",
        "GARLIC BREAD","CHICKEN ROYALE","NACHO CHEESE"
    )

    private val menuDessertHarga = intArrayOf(
        43000,42000,34000,24000,41000,34000
    )

    private val menuDessertDesc = arrayOf(
        "Sayap Ayam Panggang dengan Bumbu khan New Orleans",
        "Daging Ayam dengan Tepung Roti dan Saus Celup Garlic Butter",
        "Frankfurter Sapi, Pastry Rolls dan Saus Black Pepper BBQ",
        "Roti Panggang Mentega Beraroma Bawang Putih",
        "Stik Ayam, Chicken Popcorn, Potato Wedges dan Garlic Bread",
        "Keripik Jagung, Saus Daging Tomat dan Keju Mayo"
    )


    val listMenuDessert: ArrayList<ItemMenu>
        get() {
            val list = arrayListOf<ItemMenu>()
            for (item in menuDessertNama.indices) {
                val addMakanan = ItemMenu(
                    menuDessertNama[item],
                    menuDessertDesc[item],
                    menuDessertHarga[item],
                    menuDessertImage[item])
                list.add(addMakanan)
            }
            return list
        }

}