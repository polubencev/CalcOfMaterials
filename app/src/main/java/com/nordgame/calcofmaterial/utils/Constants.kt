package com.nordgame.calcofmaterial.utils
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nordgame.calcofmaterial.R
import com.nordgame.calcofmaterial.models.BottomNavItem

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "WoodPlank",
            icon = R.drawable.wood_plank,
            path = "woodplank"
        ),
        BottomNavItem(
            label = "FireWood",
            icon = R.drawable.firewood,
            path = "firewood"
        )
    )
}
object ColorsApp{
    val BottomBarColor = 0x95018D03
    val CardColor = 0x92018D03
    val BottomBar = 0xFF02A605
}

