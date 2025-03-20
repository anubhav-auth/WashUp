package com.anubhavauth.washup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.anubhavauth.washup.ui.theme.WashUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WashUpTheme {

                val clothList = listOf(
                    ClothListContent(name = "T-Shirts", imageId = R.drawable.tshirt, price = 8),
                    ClothListContent(
                        name = "Shirts (Casual/Formal)",
                        imageId = R.drawable.shirt,
                        price = 9
                    ),
                    ClothListContent(name = "Jeans/Denim", imageId = R.drawable.jeans, price = 9),
                    ClothListContent(
                        name = "Trousers/Chinos",
                        imageId = R.drawable.trousers,
                        price = 9
                    ),
                    ClothListContent(name = "Shorts", imageId = R.drawable.shorts, price = 8),
                    ClothListContent(name = "Sweaters", imageId = R.drawable.sweater, price = 9),
                    ClothListContent(
                        name = "Hoodies/Jackets",
                        imageId = R.drawable.jacket,
                        price = 9
                    ),
                    ClothListContent(
                        name = "Bedsheets",
                        imageId = R.drawable.bedsheet_single,
                        price = 15
                    ),
                    ClothListContent(
                        name = "Bedsheets",
                        imageId = R.drawable.bedsheet_double,
                        price = 20
                    ),
                    ClothListContent(
                        name = "Pillow Covers",
                        imageId = R.drawable.pillow_cover,
                        price = 5
                    ),
                    ClothListContent(
                        name = "Towels",
                        imageId = R.drawable.towel,
                        price = 9
                    ),
                    ClothListContent(
                        name = "Handkerchiefs",
                        imageId = R.drawable.handkerchief,
                        price = 3
                    ),
                    ClothListContent(name = "Socks", imageId = R.drawable.socks, price = 5),
                    ClothListContent(
                        name = "Inner wear",
                        imageId = R.drawable.innerwear,
                        price = 5
                    )
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingVal ->
                    ClothListMenu(
                        modifier = Modifier.padding(paddingVal),
                        clothList
                    )
                }
            }
        }
    }
}

